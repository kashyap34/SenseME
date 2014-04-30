package recommendation.artist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.codehaus.jackson.map.ObjectMapper;

import Util.Constants;
import Util.RecommendationUtil;
import dao.DatabaseOperations;
import echonest.artist.ArtistImage;

public class ItemBasedBoolRecommendArtist {
	private static Logger logger = Logger.getLogger(ItemBasedBoolRecommendArtist.class.getName());
	private RecommendationUtil recommendUtil = RecommendationUtil.getInstance();
	private Properties prop = new Properties();
	private static List<RecommendedItem> recommendations;
	private ObjectMapper mapper = new ObjectMapper();
	/*private static List<bean.Artist> itemBasedArtistList = new ArrayList<bean.Artist>();
	
	public List<bean.Artist> getItemBasedArtistList() {
		return itemBasedArtistList;
	}

	public void setItemBasedArtistList(List<bean.Artist> itemBasedArtistList) {
		this.itemBasedArtistList = itemBasedArtistList;
	}*/
	
	private static List<String> itemBasedArtistList = new ArrayList<String>();

	public List<String> getItemBasedArtistList() {
		return itemBasedArtistList;
	}

	public void setItemBasedArtistList(List<String> itemBasedArtistList) {
		this.itemBasedArtistList = itemBasedArtistList;
	}

	public List<RecommendedItem> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<RecommendedItem> recommendations) {
		this.recommendations = recommendations;
	}

	public void recommendArtistForUser(String userId) {
		try {
			File file = new File(Constants.mappingsFile);
			prop.load(new FileInputStream(Constants.propertiesFilePath));
			DatabaseOperations dao = new DatabaseOperations(prop);
			
			//Using binary similarity, neighborhood and recommendations.
			FileDataModel dataModel = new FileDataModel(file);
			ItemSimilarity itemSimilarity = new LogLikelihoodSimilarity(dataModel);
			
			Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
			recommender.getDataModel().refresh(null);
			recommender.refresh(null);
			
			// get the recommendations for the user
			long encodedUserID = recommendUtil.encodeUserId(userId);
			logger.info("Encoded user id is: " + encodedUserID);
			recommendations = recommender.recommend(encodedUserID, Constants.noOfRecommendations);
			
			System.out.println("Generating Item based recommendation for user");
			// if empty write something
			if (recommendations.size() == 0) {
				System.out.print("User ");
				System.out.print(userId);
				System.out.println(": no recommendations");
			}
			
			String artistNameInURLFormat = "", artistName, originalJSON;
			// print the list of recommendations for each
			for (RecommendedItem recommendedItem : recommendations) {
				bean.Artist artist = new bean.Artist();
				System.out.print("User ");
				System.out.print(userId);
				System.out.print(": ");
				artistName = dao.getArtistFromID((int)recommendedItem.getItemID());
				System.out.println(artistName);
				/*artistNameInURLFormat = recommendUtil.convertStringToUrlFormat(artistName);
				
				originalJSON = recommendUtil.fetchJSONFromUrl(Constants.echoNestArtistImageURL + artistNameInURLFormat);
				ArtistImage artistImage = mapper.readValue(originalJSON, ArtistImage.class);
				if(artistImage.getResponse().getImages() != null && artistImage.getResponse().getImages().size() > 0) {
					artist.setName(artistName);
					artist.setImage(artistImage.getResponse().getImages().get(0));*/
					itemBasedArtistList.add(artistName);
				/*}
				else {
					logger.severe("No image found");
				}*/
				// System.out.println(recommendedItem);
			}

			/*String artist = "";
			Iterator<String> iterator = RecommendationUtil.encodedArtists.keySet().iterator();
			while(iterator.hasNext())
			{
				System.err.println("Artist Id is: " + iterator.next());
			}*/
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
	}

}
