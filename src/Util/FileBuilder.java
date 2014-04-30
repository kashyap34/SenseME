package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;

import dao.DatabaseOperations;

import echonest.catalog.EchoNestCatalog;
import echonest.catalog.Item;

public class FileBuilder {
	
	private static Logger logger = Logger.getLogger(FileBuilder.class.getName());
	private RecommendationUtil recommendUtil = RecommendationUtil.getInstance();
	private ObjectMapper mapper = new ObjectMapper();
	private File outputFile = new File(Constants.outputArtistFile);
	private FileOutputStream toFile = null;
	private String content = "", catalogId, originalJSON, artistId, userId, csv;
	private List<Item> catalogList = null;
	private Properties prop = new Properties();
	private File outFile = new File(Constants.mappingsFile);
	
	public void generateCSV(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			
			if(outputFile.exists()) {
				toFile = new FileOutputStream(outputFile, true);
			}
			/*else {
				//erase all the contents of the file before writing data
				outputFile.delete();
				toFile = new FileOutputStream(outputFile, true);
			}*/
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		
			while((content = reader.readLine()) != null) {
				catalogId = content.substring(content.lastIndexOf(",") + 1);
				logger.info("Fetching data for Catalog with id: " + catalogId);
				originalJSON = recommendUtil.fetchJSONFromUrl(Constants.echoNestCatalogURL + catalogId);
				//Parse the JSON
				EchoNestCatalog catalog = mapper.readValue(originalJSON, EchoNestCatalog.class);
				if(catalog.getResponse().getStatus().getCode() == 0) {
					logger.info("Information found for catalog with ID: " + catalogId);
					catalogList = catalog.getResponse().getCatalog().getItems();
					userId = serializeUserId(catalog.getResponse().getCatalog().getName());

					for (Item item : catalogList) {
						artistId = item.getArtist_id();
						csv = userId + "," + artistId + "\n";
						toFile.write(csv.getBytes());
					}
					// Since EchoNest only allows 120 calls per minute wait for some time to make
					// next set of calls
				}
				Thread.sleep(500);
			}
		} catch(Exception e) {
			logger.severe("Error in generating the CSV file");
			e.printStackTrace();
		}
		
	}
	
	private String serializeUserId(String userName) {
		String temp = userName.substring(0, userName.indexOf("_"));
		return temp;
	}
	
	private void generateMappings() {
		try {
			prop.load(new FileInputStream(Constants.propertiesFilePath));
			DatabaseOperations dao = new DatabaseOperations(prop);
			
			BufferedReader reader = new BufferedReader(new FileReader(Constants.outputArtistFile));
			FileOutputStream toFile = new FileOutputStream(outFile);
			String lineRead = "", csv;
			long userID, artistID;
			String [] lineArray;
			while((lineRead = reader.readLine()) != null) {
				lineArray = lineRead.split(",");
				userID = recommendUtil.encodeUserId(lineArray[0]);
				artistID = dao.getArtistIdFromEchoNestID(lineArray[1]);
				csv = userID + "," + artistID + "\n";
				toFile.write(csv.getBytes());
			}
		} catch (Exception e) {
			logger.severe("Error in generating mappings");
			e.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) {
		FileBuilder fb = new FileBuilder();
		logger.info("Generating the CSV file......");
		fb.generateCSV(new File(Constants.inputArtistFile));
		//fb.generateMappings();
		System.out.println("File Generated");
	}
	
}
