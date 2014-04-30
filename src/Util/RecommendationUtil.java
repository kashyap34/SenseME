package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.mahout.cf.taste.impl.model.MemoryIDMigrator;

public class RecommendationUtil {
	
	private static Logger logger = Logger.getLogger(RecommendationUtil.class.getName());
	private static int userIdCount = 0;
	private int minRandomId = 1;
	private int maxRandomId = Integer.MAX_VALUE;
	private static MemoryIDMigrator mapStringsToID = new MemoryIDMigrator();
	private long userID, artistID;
	
	public static RecommendationUtil getInstance() {
		return new RecommendationUtil();
	}
	
	public long encodeUserId(String userName) {
		userID = mapStringsToID.toLongID(userName);
		mapStringsToID.storeMapping(userID, userName);
		return userID;
	}
	
	public String decodeUserId(long userId) {
		return mapStringsToID.toStringID(userId);
	}
	
	public long encodeArtist(String artistName) {
		artistID = mapStringsToID.toLongID(artistName);
		mapStringsToID.storeMapping(artistID, artistName);
		return artistID;
	}
	
	public String decodeArtistId(long artistId) {
		return mapStringsToID.toStringID(artistID);	
	}
	
	public String generateRandomId() {
		Random random = new Random();
		int randomInt = random.nextInt((maxRandomId - minRandomId) + 1) + minRandomId;
		return String.valueOf(randomInt);
	}
	
	public String fetchJSONFromUrl(String url) {
		try {
			URL pageURL = new URL(url);
	        HttpURLConnection siteConnection = (HttpURLConnection)pageURL.openConnection();
	        if(siteConnection.getResponseCode() != 400) {
				BufferedReader dis = new BufferedReader(new InputStreamReader(siteConnection.getInputStream()));
				String temp;
				StringBuffer originalJSON = new StringBuffer();

				while ((temp = dis.readLine()) != null) {
					originalJSON.append(temp);
				}

				return originalJSON.toString();
	        }
		}catch (Exception e) {
			logger.info("Error fetching JSON from given url");
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String convertStringToUrlFormat(String str) {
		String formattedStr = str;
		if (str.indexOf(" ") != -1) {
			formattedStr = formattedStr.replaceAll(" ", "%20");
		}
		if (str.indexOf("-") != -1) {
			formattedStr = formattedStr.replaceAll("-", "%2D");
		}
		if (str.indexOf("#") != -1) {
			formattedStr = formattedStr.replaceAll("#", "%23");
		}
		if (str.indexOf("$") != -1) {
			formattedStr = formattedStr.replaceAll("$", "%24");
		}
		if (str.indexOf("(") != -1) {
			formattedStr = formattedStr.replaceAll("(", "%28");
		}
		if (str.indexOf(")") != -1) {
			formattedStr = formattedStr.replaceAll(")", "%29");
		}
		if (str.indexOf(",") != -1) {
			formattedStr = formattedStr.replaceAll(",", "%2C");
		}
		if (str.indexOf("&") != -1) {
			formattedStr = formattedStr.replaceAll("&", "%26");
		}
		if (str.indexOf(".") != -1) {
			formattedStr = formattedStr.replaceAll(".", "%2E");
		}
		if (str.indexOf("*") != -1) {
			formattedStr = formattedStr.replaceAll("*", "%2A");
		}
		if (str.indexOf("!") != -1) {
			formattedStr = formattedStr.replaceAll("!", "%21");
		}
		if (str.indexOf("@") != -1) {
			formattedStr = formattedStr.replaceAll("@", "%40");
		}
		return formattedStr;
	}
	
	public String getTrackIDFromURL(String url) {
		String trackID = url;
		trackID = trackID.substring(trackID.lastIndexOf("/") + 1);
		return trackID;
	}
	
	public String fetchAndFormatJSONFromLastFm(String artistName) {
		String originalJSON = fetchJSONFromUrl(Constants.lastFmArtistURL + artistName);
		String formattedJSON = originalJSON.substring(0, originalJSON.indexOf(",\"similar\""));
		formattedJSON += "}}";
		formattedJSON = formattedJSON.replaceAll("#", "_");
		
		return formattedJSON;
	}
}
