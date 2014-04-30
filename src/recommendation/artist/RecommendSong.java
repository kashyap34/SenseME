package recommendation.artist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

import lastfm.artist.song.LastfmSongData;
import lastfm.artist.song.Track;

import org.codehaus.jackson.map.ObjectMapper;

import Util.Constants;
import Util.RecommendationUtil;

public class RecommendSong {

	private static Logger logger = Logger.getLogger(RecommendSong.class.getName());
	private RecommendationUtil recommendUtil = new RecommendationUtil();
	private String artistName;
	private ObjectMapper mapper = new ObjectMapper();
	private static List<String> songUrlList = new ArrayList<String>();
	
	public List<String> getSongUrlList() {
		return songUrlList;
	}

	public void setSongUrlList(List<String> songUrlList) {
		this.songUrlList = songUrlList;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
		logger.info("Fetching info for artist: " + artistName);
		fetchSongFromArtist(artistName);
	}

	public List<String> fetchSongFromArtist(String artistName) {
		try {
			songUrlList.clear();
			logger.info("Inside Recommend Song ");
			String artistNameInUrlFormat = recommendUtil.convertStringToUrlFormat(artistName);
			String originalJSON = recommendUtil.fetchJSONFromUrl(Constants.lastFmTopTracksURL + artistNameInUrlFormat);
			String formattedJSON = originalJSON.replaceAll("#", "_");
			formattedJSON = formattedJSON.substring(0,formattedJSON.lastIndexOf(",\"@attr\""));
			formattedJSON = formattedJSON.replaceAll("@", "_");
			formattedJSON += "}}";
			
			logger.info("Formatted JSON: " + formattedJSON);

			LastfmSongData songData = mapper.readValue(formattedJSON,LastfmSongData.class);
			List<Track> trackList = songData.getToptracks().getTrack();
			
			for(Track track: trackList) {
				songUrlList.add(track.getUrl());
				System.out.println("Song URL is: " + track.getUrl());
			}
			//return songUrlList;
			FacesContext.getCurrentInstance().getExternalContext().redirect("Song.xhtml");
		} catch(Exception e) {
			logger.severe("Error in fetching the song data for artist: " + artistName);
			e.printStackTrace();
		}
		return null;
	}
}
