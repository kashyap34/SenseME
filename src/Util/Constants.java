package Util;

public class Constants {
	
	public static final int noOfRecommendations = 50;
	public static final String echoNestToken = "TGWSPLYEMTMBGOPSV";
	public static final String lastfmToken = "09549500a87da299630fce213d877c3c";
	public static String echoNestArtistURL = "http://developer.echonest.com/api/v4/artist/profile?api_key=" + echoNestToken + "&format=json&name=";
	public static String fbMusicListensURL = "https://graph.facebook.com/me/music.listens?access_token=";
	public static String echoNestCatalogURL = "http://developer.echonest.com/api/v4/catalog/read?api_key=TGWSPLYEMTMBGOPSV&format=json&id=";
	public static final String inputArtistFile ="/home/kashyap/Documents/CmpE239/SenseMe/datasets/taste_profile_usercat_120k.txt";
	public static final String outputArtistFile = "/home/kashyap/Documents/CmpE239/SenseMe/datasets/userArtists.csv";
	public static final String propertiesFilePath = "/home/kashyap/Documents/CmpE239/SenseMe/datasets/DBProperties.properties";
	public static final String mappingsFile = "/home/kashyap/Documents/CmpE239/SenseMe/datasets/userVsArtistMappings.csv";
	public static final String spotifyTrackURL = "http://ws.spotify.com/lookup/1/.json?uri=spotify:track:";
	public static String echoNestArtistImageURL = "http://developer.echonest.com/api/v4/artist/images?api_key=" + echoNestToken + "&results=1&license=unknown&name=";
	public static String lastFmArtistURL = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&api_key=09549500a87da299630fce213d877c3c&format=json&artist=";
	public static String lastFmTopTracksURL = "http://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&api_key=" + lastfmToken + 
												"&format=json&limit=5&artist=";
}
