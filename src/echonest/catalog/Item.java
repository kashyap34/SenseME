package echonest.catalog;

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
"song_name",
"artist_id",
"artist_name",
"play_count",
"last_modified",
"date_added",
"foreign_id",
"song_id",
"request"
})
public class Item {

@JsonProperty("song_name")
private String song_name;
@JsonProperty("artist_id")
private String artist_id;
@JsonProperty("artist_name")
private String artist_name;
@JsonProperty("play_count")
private long play_count;
@JsonProperty("last_modified")
private String last_modified;
@JsonProperty("date_added")
private String date_added;
@JsonProperty("foreign_id")
private String foreign_id;
@JsonProperty("song_id")
private String song_id;
@JsonProperty("request")
private Request request;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("song_name")
public String getSong_name() {
return song_name;
}

@JsonProperty("song_name")
public void setSong_name(String song_name) {
this.song_name = song_name;
}

@JsonProperty("artist_id")
public String getArtist_id() {
return artist_id;
}

@JsonProperty("artist_id")
public void setArtist_id(String artist_id) {
this.artist_id = artist_id;
}

@JsonProperty("artist_name")
public String getArtist_name() {
return artist_name;
}

@JsonProperty("artist_name")
public void setArtist_name(String artist_name) {
this.artist_name = artist_name;
}

@JsonProperty("play_count")
public long getPlay_count() {
return play_count;
}

@JsonProperty("play_count")
public void setPlay_count(long play_count) {
this.play_count = play_count;
}

@JsonProperty("last_modified")
public String getLast_modified() {
return last_modified;
}

@JsonProperty("last_modified")
public void setLast_modified(String last_modified) {
this.last_modified = last_modified;
}

@JsonProperty("date_added")
public String getDate_added() {
return date_added;
}

@JsonProperty("date_added")
public void setDate_added(String date_added) {
this.date_added = date_added;
}

@JsonProperty("foreign_id")
public String getForeign_id() {
return foreign_id;
}

@JsonProperty("foreign_id")
public void setForeign_id(String foreign_id) {
this.foreign_id = foreign_id;
}

@JsonProperty("song_id")
public String getSong_id() {
return song_id;
}

@JsonProperty("song_id")
public void setSong_id(String song_id) {
this.song_id = song_id;
}

@JsonProperty("request")
public Request getRequest() {
return request;
}

@JsonProperty("request")
public void setRequest(Request request) {
this.request = request;
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
