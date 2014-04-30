package facebook.graph;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
"song",
"album",
"playlist"
})
public class Musicdata {

@JsonProperty("song")
private Song song;
@JsonProperty("album")
private Album album;
@JsonProperty("playlist")
private Playlist playlist;

private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("song")
public Song getSong() {
return song;
}

@JsonProperty("song")
public void setSong(Song song) {
this.song = song;
}

@JsonProperty("album")
public Album getAlbum() {
return album;
}

@JsonProperty("album")
public void setAlbum(Album album) {
this.album = album;
}

@JsonProperty("playlist")
public Playlist getPlaylist() {
	return playlist;
}

@JsonProperty("playlist")
public void setPlaylist(Playlist playlist) {
	this.playlist = playlist;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return HashCodeBuilder.reflectionHashCode(this);
}

@Override
public boolean equals(Object other) {
return EqualsBuilder.reflectionEquals(this, other);
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperties(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
