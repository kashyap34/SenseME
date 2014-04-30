package user.details;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import lastfm.similar.artist.Artist;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

import recommendation.artist.ItemBasedBoolRecommendArtist;
import recommendation.artist.UserBasedBoolRecommendArtist;
import spotify.track.SpotifyTrackInfo;
import Util.Constants;
import Util.RecommendationUtil;
import dao.DatabaseOperations;
import echonest.catalog.EchoNestCatalog;
import facebook.graph.Data;
import facebook.graph.FacebookMusicGraph;
import facebook.graph.Musicdata;
import facebook.graph.Song;

public class UserInformation {
	
	private String userName="", password = "", fbAccessToken = "Token is: ";
	private List<Page> musicList;
	private final static Logger logger = Logger.getLogger(UserInformation.class.getName());
	private List<Data> dataList;
	private boolean isDone = false;
	private List<String> friendName = new ArrayList<String>();
	private List<Page> friendMusicList;
	private final String lastfmAccessToken = "09549500a87da299630fce213d877c3c";
	private List<String> lastFmJSONList = new ArrayList<String>();
	private List<Artist> matchingArtistList = new ArrayList<Artist>();
	private FileOutputStream toFile = null;
	private File file = new File(Constants.mappingsFile);
	private RecommendationUtil recommendUtil = RecommendationUtil.getInstance();
	private List<Artist> similarArtistList = null;
	private String originalEchoNestJSON, originalSpotifyJSON, artistTitle, trackID, formattedSpotifyJSON;
	private ObjectMapper mapper = new ObjectMapper();
	private Properties prop = new Properties();
    
	
	public List<String> getFriendName() {
		return friendName;
	}

	public void setFriendName(List<String> friendName) {
		this.friendName = friendName;
	}

	public List<Page> getFriendMusicList() {
		return friendMusicList;
	}

	public void setFriendMusicList(List<Page> friendMusicList) {
		this.friendMusicList = friendMusicList;
	}

	public List<Data> getDataList() {
		return dataList;
	}

	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}

	public List<Page> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<Page> musicList) {
		this.musicList = musicList;
	}

	public String getAccessToken() {
		logger.info("Getting the Access Token with value: " + fbAccessToken);
		return fbAccessToken;
	}

	public void setAccessToken(String accessToken) {
		logger.info("Setting the Access Token with value: " + accessToken);
		this.fbAccessToken = accessToken;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void process(AjaxBehaviorEvent event) {
		setAccessToken(fbAccessToken);
	}
	
	public String fetchUserDetails() {
		try {
			logger.info("Using user's FB credentials to Login");

			FacebookTemplate facebook = new FacebookTemplate(fbAccessToken);

			FacebookProfile myProfile = facebook.userOperations().getUserProfile();
			userName = myProfile.getName();

			logger.info("Hi, my name is: " + myProfile.getName());
			
			if(file.exists()) {
				logger.info("File exists");
			}
			toFile = new FileOutputStream(file, true);
			//Configure the mapper to accept single value as an array 
	        //to handle the case where no mathching artist is found
	        mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
			
			String csv = "";
			String artistName = null;
			
			musicList = facebook.likeOperations().getMusic();
			for (Page musicPage : musicList) {
				System.out.println(String.format("%s likes musician %s",
						myProfile.getName(), musicPage.getName()));
				/*artistName = recommendUtil.convertStringToUrlFormat(musicPage.getName());
				originalEchoNestJSON = recommendUtil.fetchJSONFromUrl(Constants.echoNestArtistURL + artistName);
				EchoNestCatalog catalog = mapper.readValue(originalEchoNestJSON, EchoNestCatalog.class);*/
				//TODO Should create another file for item based similarity
				/*csv = myProfile.getId() + "," + catalog.getResponse().getArtist().getId() + "\n";
				//write the comma separated values for this user to file
				toFile.write(csv.getBytes());*/
			}

			String originalJSON = recommendUtil.fetchJSONFromUrl(Constants.fbMusicListensURL + fbAccessToken);

			//Replace all but first occurrence of data with musicdata to avoid conflicts while parsing
	        StringBuffer formattedJSON = new StringBuffer();
	        Pattern pattern = Pattern.compile("data");
	        Matcher matcher = pattern.matcher(originalJSON.toString());
	        int count = 0;
	        while(matcher.find()) {
	        	if(count++ > 0) {
	        		matcher.appendReplacement(formattedJSON, "musicdata");
	        	}
	        }
	        matcher.appendTail(formattedJSON);
	        
	        System.out.println("JSON Contents: " + originalJSON.toString());
	        System.out.println("Fromatted JSON Contents: " + formattedJSON.toString());
	        
	        prop.load(new FileInputStream(Constants.propertiesFilePath));
			DatabaseOperations dao = new DatabaseOperations(prop);
			
	        //Reading the JSON into the FacebookMusicGraph and then retrieving the relevant values
	        FacebookMusicGraph musicGraph = mapper.readValue(formattedJSON.toString(), FacebookMusicGraph.class);
	        
	        List<Data> dataList = musicGraph.getData();
	        
	        for(Data data : dataList) {
	        	//Extract the music data from the raw data;
	        	Musicdata musicData = data.getMusicdata();
	        	Song songData = musicData.getSong();
	        	if(songData != null) {
					trackID = recommendUtil.getTrackIDFromURL(songData.getUrl());
					// Fetch the artist ID from EchoNest
					if (!trackID.isEmpty() || !(trackID == null)) {
						originalSpotifyJSON = recommendUtil.fetchJSONFromUrl(Constants.spotifyTrackURL + trackID);
						formattedSpotifyJSON = originalSpotifyJSON.replaceAll("-", "_");
						SpotifyTrackInfo track = mapper.readValue(formattedSpotifyJSON, SpotifyTrackInfo.class);
						artistTitle = track.getTrack().getArtists().get(0).getName();
						artistName = recommendUtil.convertStringToUrlFormat(artistTitle);
						logger.info("Artist name in URL format is: " + artistName);
						originalEchoNestJSON = recommendUtil.fetchJSONFromUrl(Constants.echoNestArtistURL + artistName);
						EchoNestCatalog catalog = mapper.readValue(originalEchoNestJSON, EchoNestCatalog.class);
						if (catalog.getResponse().getStatus().getCode() == 0) {
							echonest.catalog.Artist artist = catalog.getResponse().getArtist();
							if (artist != null) {
								System.out.println(String.format("%s listens to %s", myProfile.getFirstName(), songData.getTitle()));
								csv = recommendUtil.encodeUserId(myProfile
										.getId())
										+ ","
										+ dao.getArtistIdFromEchoNestID(catalog
												.getResponse().getArtist()
												.getId()) + "\n";
								// write the comma separated values for this
								// user to
								// file
								toFile.write(csv.getBytes());
							}
						}
					}
	        	}
	        }
			
	        //Get the profiles of friends.
	        FriendInformation friend = new FriendInformation();
	        friend.setAccessToken(fbAccessToken);
	        isDone = friend.fetchFriendDetails();
	        //isDone = fetchFriendDetails();
	        
	        //Retrieve similar artists for this user using last.fm API
	        /*for(String jsonFromLastFm : lastFmJSONList) {
	        	logger.info("Json String from last.fm is: " + jsonFromLastFm);
    			SimilarArtist userSimilarArtist = mapper.readValue(jsonFromLastFm, SimilarArtist.class);
    			
    			//check if the user's artist has similar artists in last.fm
	        	if(userSimilarArtist.getSimilarartists().getText() == null) {
					similarArtistList = userSimilarArtist.getSimilarartists().getArtist();

					// only retrieve those artists who have a match value greater than 0.5
					for (Artist artist : similarArtistList) {
						if (Double.parseDouble(artist.getMatch()) > 0.5) {
							matchingArtistList.add(artist);
							System.out.println(String.format("Matching artist %s found for user %s",artist.getName(), myProfile.getName()));
						}
					}
	        	}
	        }*/
	        
        	UserBasedBoolRecommendArtist userBoolRecArtist = new UserBasedBoolRecommendArtist();
        	List<RecommendedItem> recommendationList = userBoolRecArtist.recommendArtistForUser(myProfile.getId());
        	
        	ItemBasedBoolRecommendArtist itemBoolRecArtist = new ItemBasedBoolRecommendArtist();
        	itemBoolRecArtist.recommendArtistForUser(myProfile.getId());

        	MoodProcessor mood = new MoodProcessor();
        	mood.processMood(recommendationList);
        	
		}catch (Exception e) {
			logger.severe("Exception in fetching the music data for the user");
			logger.severe(e.getMessage());
			e.printStackTrace();
			}
		finally {
			try {
				toFile.close();
			} catch (IOException e) {
				logger.severe("Error in closing the file descriptor");
				e.printStackTrace();
			}
		}
		
		if(isDone) {
        	try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Music.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//return "Music.jsp?faces-redirect=true";
        }
		
		return "";
	}
	
	/*public boolean fetchFriendDetails() {
		//TODO use hashmap for better  UI display
		FacebookTemplate facebook = new FacebookTemplate(fbAccessToken);
		
		List<String> friendIdList = facebook.friendOperations().getFriendIds();
		
		for(String friendId : friendIdList) {
			FacebookProfile friendProfile = facebook.userOperations().getUserProfile(friendId);
			logger.info("Hi, friend name is: " + friendProfile.getName() + " with Fb ID: " + friendId);
			friendName.add(friendProfile.getName());
			
			friendMusicList = facebook.likeOperations().getMusic(friendId);
			
			if(friendMusicList != null) {
				logger.info("Friend's Music List is not null");
				logger.info("Page count is: " + friendMusicList.size());
				for (Page musicPage : friendMusicList) {
					logger.info("Page name is: " + musicPage.getName());
					System.out.println(String.format("%s likes musician %s",
							friendProfile.getName(), musicPage.getName()));
				}
			}
			else
				logger.severe("Friend's Music List is null");
			String url = "https://graph.facebook.com/" + friendId + "/music.listens?access_token=" + fbAccessToken;
			
			try {
				URL pageURL = new URL(url);
		        URLConnection siteConnection = pageURL.openConnection();
		        BufferedReader dis = new BufferedReader(new InputStreamReader(siteConnection.getInputStream()));
		        String temp;
		        StringBuffer originalJSON = new StringBuffer(); 
		        
		        while((temp = dis.readLine()) != null) {
		        	originalJSON.append(temp);
		        }
		        
		        //Replace all but first occurrence of data with musicdata to avoid conflicts while parsing
		        StringBuffer formattedJSON = new StringBuffer();
		        Pattern pattern = Pattern.compile("data");
		        Matcher matcher = pattern.matcher(originalJSON.toString());
		        int count = 0;
		        while(matcher.find()) {
		        	if(count++ > 0) {
		        		matcher.appendReplacement(formattedJSON, "musicdata");
		        	}
		        }
		        matcher.appendTail(formattedJSON);
		        
		        System.out.println("JSON Contents: " + originalJSON.toString());
		        System.out.println("Fromatted JSON Contents: " + formattedJSON.toString());
		        
		        //Reading the JSON into the FacebookMusicGraph and then retrieving the relevant values
		        ObjectMapper mapper = new ObjectMapper();
		        FacebookMusicGraph musicGraph = mapper.readValue(formattedJSON.toString(), FacebookMusicGraph.class);
		        
		        List<Data> dataList = musicGraph.getData();
		        
		        for(Data data : dataList) {
		        	//Extract the music data from the raw data;
		        	Musicdata musicData = data.getMusicdata();
		        	Song songData = musicData.getSong();
		        	System.out.println(String.format("%s listens to %s", friendProfile.getFirstName(), songData.getTitle()));
		        }
			}catch (Exception e) {
				logger.info("Exception in fetching the music data for the user");
				logger.warning(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}*/
	

}
