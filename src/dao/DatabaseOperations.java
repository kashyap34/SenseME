package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Logger;

import java.sql.Connection;

public class DatabaseOperations {
	Properties prop = new Properties();
	private static final Logger logger = Logger.getLogger(DatabaseOperations.class.getName());
	private Connection conn = null;
	private final String url = "jdbc:mysql://localhost/SenseMe";
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private HashMap<String, Integer> artistVsIdMap = new HashMap<String, Integer>();
	
	public DatabaseOperations(Properties prop) {
		this.prop = prop;
	}
	
	public void initConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, prop);
		} catch (ClassNotFoundException e) {
			logger.severe("Error in loading the MySql Driver");
			e.printStackTrace();
		} catch(SQLException sqe) {
			logger.severe("Error in creating the connection to the DB");
			sqe.printStackTrace();
		}
	}
	
	/*
	 * Retrieve all the artists from DB and store in map as a key-value pair
	 * key:   EchoNest artist Id
	 * Value: artist name
	 */
	public HashMap<String, Integer> getAllArtists(String mood) {
		try {
			if(conn == null) {
				initConnection();
			} 
			else {
				String artistQuery = "Select * from SenseMe.artists a, SenseMe." + mood + " m where m.echonest_artist_id=a.echonest_id";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(artistQuery);
				
				while(rs.next()) {
					artistVsIdMap.put(rs.getString("echonest_id"), rs.getInt(1));
				}
				
				return artistVsIdMap;
			}
			
		} catch(Exception e) {
			logger.severe("Error in retrieving all the artists");
			e.printStackTrace();
		} 
		
		return artistVsIdMap;
	}
	
	public HashMap<Integer, Integer> getArtistsBasedOnMood(String mood) {
		try{
			logger.info("Current mood of user is: " + mood);
			initConnection();
			HashMap<String, Integer> artistsMap = getAllArtists(mood);
			HashMap<Integer, Integer> top50Artists = new HashMap<Integer, Integer>();
			if(conn != null) {
				String moodQuery = "Select echonest_artist_id, lastmood_index from " + mood ;
				HashMap<Integer, Integer> moodVsArtistMap = new HashMap<Integer, Integer>();
				stmt = conn.createStatement();
				rs= stmt.executeQuery(moodQuery);
				
				//make sure that the artistVsIdMap is populated
				if(artistsMap.size() > 0) {
					while(rs.next()) {
						int moodIndex = rs.getInt("lastmood_index");
						if(artistsMap.containsKey(rs.getString("echonest_artist_id"))) {
							//Retrieve the artist name based on its echonest artist id
							moodVsArtistMap.put(artistsMap.get(rs.getString("echonest_artist_id")), moodIndex);
						}
					}
					top50Artists = sortHashMapByDescOrder(moodVsArtistMap);
				}
				
				return top50Artists;
				
			}
			else
				logger.warning("Connection is null");
		} catch(Exception e) {
			logger.severe("Error in retrieving the artists for the given mood");
		} finally {
			try {
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				logger.severe("Error in closing the database connection");
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public long getArtistIdFromEchoNestID(String echoNestID) {
		try {
			initConnection();
			String artistIDQuery = "Select id from artists where echonest_id=?";
			pstmt = conn.prepareStatement(artistIDQuery);
			pstmt.setString(1, echoNestID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				long artistID = (long)rs.getInt("id");
				return artistID;
			}
		} catch(Exception e) {
			logger.severe("Error in fetching the id for the given echoNestID: " + echoNestID);
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				logger.severe("Error in closing the connection");
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	
	public String getEchoNestID(int encodedArtistID) {
		try {
			initConnection();
			String echoNestIDQuery = "Select echonest_id from artists where id=?";
			pstmt = conn.prepareStatement(echoNestIDQuery);
			pstmt.setInt(1, encodedArtistID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String echoNestID = rs.getString("echonest_id");
				return echoNestID;
			}
			
		} catch(Exception e) {
			logger.severe("Error in fetching the EchoNest ID for artist ID: " + encodedArtistID);
			e.printStackTrace();
		} 
		return "";
	}
	
	public String getArtistFromID(int encodedArtistID) {
		try {
			initConnection();
			String echoNestID = getEchoNestID(encodedArtistID);
			String echoNestIDQuery = "Select artist_name from artists where echonest_id=?";
			pstmt = conn.prepareStatement(echoNestIDQuery);
			pstmt.setString(1, echoNestID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String artistName = rs.getString("artist_name");
				return artistName;
			}
			
		} catch(Exception e) {
			logger.severe("Error in fetching the artist name for artist ID: " + encodedArtistID);
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				logger.severe("Error in closing the connection");
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/*
	 * Returns only the top 50 artists and their mood index in descending order
	 */
	private HashMap<Integer, Integer> sortHashMapByDescOrder(HashMap<Integer, Integer> artistVsMoodIndexMap) {
		int count = 1;
		List<Entry<Integer, Integer>> artistVsMoodIndexList = new LinkedList<Entry<Integer,Integer>>(artistVsMoodIndexMap.entrySet());
		
		//sort in descending order
		Collections.sort(artistVsMoodIndexList, new Comparator<Entry<Integer, Integer>>() {
			public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
				return entry2.getValue().compareTo(entry1.getValue());
			}
		});
		
		HashMap<Integer, Integer> sortedArtistVsMoodIndexMap = new HashMap<Integer, Integer>();
		for(Entry<Integer, Integer> entry : artistVsMoodIndexList) {
			/*count++;
			if(count > 50) {
				break;
			}
			else {*/
				sortedArtistVsMoodIndexMap.put(entry.getKey(), entry.getValue());
			//}
		}
		return sortedArtistVsMoodIndexMap;
	}

}
