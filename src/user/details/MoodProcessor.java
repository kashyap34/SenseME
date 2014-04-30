package user.details;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.faces.event.ValueChangeEvent;

import lastfm.artist.ArtistDetails;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.codehaus.jackson.map.ObjectMapper;

import bean.Artist;

import Util.Constants;
import Util.RecommendationUtil;

import dao.DatabaseOperations;

public class MoodProcessor {
	private static final Logger logger = Logger.getLogger(MoodProcessor.class.getName());
	private Properties prop = new Properties();
	private RecommendationUtil recommendUtil = new RecommendationUtil();
	private ObjectMapper mapper = new ObjectMapper();
	private static List<String> moodArtistList = new ArrayList<String>();
	private static String mood;
	
	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public List<String> getMoodArtistList() {
		return moodArtistList;
	}

	public void setMoodArtistList(List<String> moodArtistList) {
		this.moodArtistList = moodArtistList;
	}
	
	public void changeMood(ValueChangeEvent ve) throws IOException {
		this.mood = (String)ve.getNewValue();
	}
	
	public void processMood(List<RecommendedItem> recommendationList) {
		try {
			prop.load(new FileInputStream(Constants.propertiesFilePath));
			DatabaseOperations dao = new DatabaseOperations(prop);
			HashMap<Integer, Integer> artistVsMoodIndexMap = dao.getArtistsBasedOnMood(mood);
			String formattedJSON, artistNameInURLFormat, artistName;
			Artist artist = new Artist();
			
			for(RecommendedItem recommendation : recommendationList) {
				if(artistVsMoodIndexMap.containsKey((int)recommendation.getItemID())) {
					artistName = dao.getArtistFromID((int)recommendation.getItemID());
					System.out.println("Recommendation based on user's mood is: " + artistName);
					/*artistNameInURLFormat = recommendUtil.convertStringToUrlFormat(artistName);
					formattedJSON = recommendUtil.fetchAndFormatJSONFromLastFm(artistNameInURLFormat);
					ArtistDetails artistDetails = mapper.readValue(formattedJSON, ArtistDetails.class);
					if(artistDetails.getArtist() != null && artistDetails.getArtist().getImage().size() > 0) {
						artist.setName(artistName);
						artist.setImageURL(artistDetails.getArtist().getImage().get(0).get_text());*/
						moodArtistList.add(artistName);
					/*}
					else {
						logger.severe("No image found");
					}*/
				}
			}
			
		} catch(Exception e) {
			logger.severe("Error in proceesing mood");
			e.printStackTrace();
		}

	}

}
