package recommendation.artist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.model.tagcloud.TagCloudModel;

import Util.Constants;
import Util.RecommendationUtil;
import dao.DatabaseOperations;

public class UserBasedBoolRecommendArtist {
	private static Logger logger = Logger.getLogger(UserBasedBoolRecommendArtist.class.getName());
	private RecommendationUtil recommendUtil = RecommendationUtil.getInstance();
	private Properties prop = new Properties();
	private static List<RecommendedItem> recommendations;
	private ObjectMapper mapper = new ObjectMapper();
	private RecommendSong recSong = new RecommendSong();
	//private static List<bean.Artist> userBasedArtistList = new ArrayList<bean.Artist>();
	private static bean.Artist selectedArtist;
	private static List<String> userBasedArtistList = new ArrayList<String>();
	
	public List<String> getUserBasedArtistList() {
		return userBasedArtistList;
	}

	public void setUserBasedArtistList(List<String> userBasedArtistList) {
		this.userBasedArtistList = userBasedArtistList;
	}

	public bean.Artist getSelectedArtist() {
		return selectedArtist;
	}

	public void setSelectedArtist(bean.Artist selectedArtist) {
		this.selectedArtist = selectedArtist;
	}

	/*public List<bean.Artist> getUserBasedArtistList() {
		return userBasedArtistList;
	}

	public void setUserBasedArtistList(List<bean.Artist> userBasedArtistList) {
		this.userBasedArtistList = userBasedArtistList;
	}
*/	
	private static List<String> songUrlList;
	
	public List<String> getSongUrlList() {
		return songUrlList;
	}

	public void setSongUrlList(List<String> songUrlList) {
		UserBasedBoolRecommendArtist.songUrlList = songUrlList;
	}

	public List<RecommendedItem> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<RecommendedItem> recommendations) {
		this.recommendations = recommendations;
	}

	public List<RecommendedItem> recommendArtistForUser(String userId) {
		try {
			File file = new File(Constants.mappingsFile);
			prop.load(new FileInputStream(Constants.propertiesFilePath));
			DatabaseOperations dao = new DatabaseOperations(prop);
			
			//Initialize the MemoryIDMigrator to create a mapping for string to long IDs
			//initMappings(file);
			
			//Using boolean similarity, neighborhood and recommendations.
			FileDataModel dataModel = new FileDataModel(file);
			UserSimilarity userSimilarity = new TanimotoCoefficientSimilarity(dataModel);
			//UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.2, userSimilarity, dataModel);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(50, userSimilarity, dataModel);
			Recommender recommender = new GenericBooleanPrefUserBasedRecommender(dataModel, neighborhood, userSimilarity);
			recommender.getDataModel().refresh(null);
			recommender.refresh(null);
			
			// get the recommendations for the user
			long encodedUserID = recommendUtil.encodeUserId(userId);
			logger.info("Encoded user id is: " + encodedUserID);
			recommendations = recommender.recommend(encodedUserID, Constants.noOfRecommendations);
			
			mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			
			System.out.println("Generating User based recommendation for user");
			// if empty write something
			if (recommendations.size() == 0) {
				System.out.print("User ");
				System.out.print(userId);
				System.out.println(": no recommendations");
			}
			String artistNameInURLFormat = "", artistName, originalJSON, formattedJSON;
			// print the list of recommendations for each
			for (RecommendedItem recommendedItem : recommendations) {
				bean.Artist artist = new bean.Artist();
				System.out.print("User ");
				System.out.print(userId);
				System.out.print(": ");
				artistName = dao.getArtistFromID((int)recommendedItem.getItemID());
				System.out.println(artistName);
				artistNameInURLFormat = recommendUtil.convertStringToUrlFormat(artistName);
				
				//songUrlList = recSong.fetchSongFromArtist(artistNameInURLFormat);
				/*formattedJSON = recommendUtil.fetchAndFormatJSONFromLastFm(artistNameInURLFormat);
				ArtistDetails artistDetails = mapper.readValue(formattedJSON, ArtistDetails.class);
				if(artistDetails.getArtist() != null && artistDetails.getArtist().getImage().size() > 0) {
					artist.setName(artistName);
					artist.setImageURL(artistDetails.getArtist().getImage().get(0).get_text());*/
				//artist.setName(artistName);
				//artist.setSongURLList(songUrlList);
					userBasedArtistList.add(artistName);
				/*}
				else {
					logger.severe("No image found");
				}*/
				// System.out.println(recommendedItem);
			}
			
			return recommendations;
		} catch(FileNotFoundException e) {
			logger.severe("Error in finding  file");
			e.printStackTrace();
		} catch (IOException e) {
			logger.severe("Exception in creating the Data Model");
			e.printStackTrace();
		} catch (TasteException e) {
			logger.severe("Error in creating the recommender");
			e.printStackTrace();
		}
		return null;
	}
	
	public void initMappings(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String lineRead = "";
			
			while((lineRead = br.readLine()) != null) {
				logger.info("Line Read: " + lineRead);
				String[] columnName = lineRead.split(",");
				if(!columnName[0].equalsIgnoreCase("123456789")) {
					recommendUtil.encodeUserId(columnName[0]);
				}
				recommendUtil.encodeArtist(columnName[1]);
			}
		} catch (FileNotFoundException e) {
			logger.severe("File not found while generating the mappings");
			e.printStackTrace();
		} catch (IOException e) {
			logger.severe("Error in reading file");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UserBasedBoolRecommendArtist ubr = new UserBasedBoolRecommendArtist();
		ubr.recommendArtistForUser("7c0459b31e5f2f8d416be40cb4da108b7d7666e9");
	}
}
