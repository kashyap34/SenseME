package user.details;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

import spotify.track.SpotifyTrackInfo;

import dao.DatabaseOperations;

import Util.Constants;
import Util.RecommendationUtil;
import echonest.catalog.Artist;
import echonest.catalog.EchoNestCatalog;
import facebook.graph.Data;
import facebook.graph.FacebookMusicGraph;
import facebook.graph.Musicdata;
import facebook.graph.Song;

public class FriendInformation {
	private String accessToken;
	private static Logger logger = Logger.getLogger(FriendInformation.class.getName());
	private static List<String> friendName = new ArrayList<String>();
	private static List<Page> musicList;
	private static List<List<Page>> friendMusicList = new ArrayList<List<Page>>();
	private static List<List<Data>> friendDataList = new ArrayList<List<Data>>();
	private FileOutputStream toFile = null;
	private String csv = "";
	private RecommendationUtil recommendUtil = RecommendationUtil.getInstance();
	private ObjectMapper mapper = new ObjectMapper();
	private String artistName, originalEchoNestJSON, originalSpotifyJSON, artistTitle, formattedSpotifyJSON, trackID;
	private Properties prop = new Properties();
	
	
	public List<List<Data>> getFriendDataList() {
		return friendDataList;
	}

	public void setFriendDataList(List<List<Data>> friendDataList) {
		this.friendDataList = friendDataList;
	}

	public List<Page> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<Page> musicList) {
		this.musicList = musicList;
	}

	public List<String> getFriendName() {
		return friendName;
	}
	
	public List<List<Page>> getFriendMusicList() {
		return friendMusicList;
	}

	public void setFriendMusicList(List<List<Page>> friendMusicList) {
		this.friendMusicList = friendMusicList;
	}

	public void setFriendName(List<String> friendName) {
		this.friendName = friendName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public boolean fetchFriendDetails() {
		try {
			FacebookTemplate facebook = new FacebookTemplate(accessToken);
		
			List<String> friendIdList = facebook.friendOperations().getFriendIds();
			toFile = new FileOutputStream(Constants.mappingsFile, true);
		
			for(String friendId : friendIdList) {
				FacebookProfile friendProfile = facebook.userOperations().getUserProfile(friendId);
				logger.info("Hi, friend name is: " + friendProfile.getName() + " with Fb ID: " + friendId);
				friendName.add(friendProfile.getName());
			
				musicList = facebook.likeOperations().getMusic(friendId);
				friendMusicList.add(musicList);
				
				if(musicList != null) {
					logger.info("Friend's Music List is not null");
					logger.info("Page count is: " + musicList.size());
					for (Page musicPage : musicList) {
						logger.info("Page name is: " + musicPage.getName());
						System.out.println(String.format("%s likes musician %s",
							friendProfile.getName(), musicPage.getName()));
						/*csv = friendProfile.getId() + "," + recommendUtil.encodeArtist(musicPage.getName()) + "\n";
						//write the comma separated values for this user's friend in to file
						toFile.write(csv.getBytes());*/
					}
				}
				else
					logger.severe("Friend's Music List is null");
				
				String url = "https://graph.facebook.com/" + friendId + "/music.listens?access_token=" + accessToken;
			
				String originalJSON = recommendUtil.fetchJSONFromUrl(url);
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
		        friendDataList.add(dataList);
		        
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
								Artist artist = catalog.getResponse().getArtist();
								if (artist != null) {
									System.out.println(String.format("%s listens to %s", friendProfile.getFirstName(), songData.getTitle()));
									csv = recommendUtil.encodeUserId(friendProfile.getId())
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
			}
			}catch (Exception e) {
				logger.info("Exception in fetching the music data for the user");
				logger.warning(e.getMessage());
				e.printStackTrace();
				return false;
			}
			finally {
				try {
					toFile.close();
				} catch (IOException e) {
					logger.severe("Error in closing the file descriptor");
					e.printStackTrace();
				}
			}
			return true;
	}
}
