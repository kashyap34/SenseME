
package lastfm.similar.artist;

import java.util.List;

public class Similarartists{
   	private attr attr;
   	private List<Artist> artist;
   	
   	//Below two are set when there are not similar artists found
   	private String text;
   	private String artistName;
   	
   	public Similarartists() {
   		//no-arg constructor
   	}
   	public Similarartists(String id) {
   		//do nothing
   	}

 	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public attr getattr(){
		return this.attr;
	}
	public void setattr(attr attr){
		this.attr = attr;
	}
 	public List<Artist> getArtist(){
		return this.artist;
	}
	public void setArtist(List<Artist> artist){
		this.artist = artist;
	}
}
