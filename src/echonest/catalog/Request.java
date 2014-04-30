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
"item_id",
"artist_id",
"song_id"
})
public class Request {

@JsonProperty("item_id")
private String item_id;
@JsonProperty("artist_id")
private String artist_id;
@JsonProperty("song_id")
private String song_id;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("item_id")
public String getItem_id() {
return item_id;
}

@JsonProperty("item_id")
public void setItem_id(String item_id) {
this.item_id = item_id;
}

@JsonProperty("artist_id")
public String getArtist_id() {
return artist_id;
}

@JsonProperty("artist_id")
public void setArtist_id(String artist_id) {
this.artist_id = artist_id;
}

@JsonProperty("song_id")
public String getSong_id() {
return song_id;
}

@JsonProperty("song_id")
public void setSong_id(String song_id) {
this.song_id = song_id;
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
