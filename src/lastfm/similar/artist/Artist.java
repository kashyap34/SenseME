
package lastfm.similar.artist;

import java.util.List;

public class Artist{
   	private List image;
   	private String match;
   	private String mbid;
   	private String name;
   	private String streamable;
   	private String url;

   	public Artist() {
   		//no-arg constructor
   	}
   	
   	public Artist(String artist) {
   		//do nothing
   	}
   	
 	public List getImage(){
		return this.image;
	}
	public void setImage(List image){
		this.image = image;
	}
 	public String getMatch(){
		return this.match;
	}
	public void setMatch(String match){
		this.match = match;
	}
 	public String getMbid(){
		return this.mbid;
	}
	public void setMbid(String mbid){
		this.mbid = mbid;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getStreamable(){
		return this.streamable;
	}
	public void setStreamable(String streamable){
		this.streamable = streamable;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}
