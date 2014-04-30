package bean;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Artist {
	
	private static String name;
	private static echonest.artist.Image image;
	private static String imageURL;
	private static List<String> songURLList = new ArrayList<String>();
	
	public List<String> getSongURLList() {
		return songURLList;
	}
	public void setSongURLList(List<String> songURLList) {
		this.songURLList = songURLList;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public echonest.artist.Image getImage() {
		return image;
	}
	public void setImage(echonest.artist.Image image2) {
		this.image = image2;
	}
	
	
}
