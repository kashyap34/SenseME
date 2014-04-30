package spotify.track;

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
"track",
"info"
})
public class SpotifyTrackInfo {

@JsonProperty("track")
private Track track;
@JsonProperty("info")
private Info info;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("track")
public Track getTrack() {
return track;
}

@JsonProperty("track")
public void setTrack(Track track) {
this.track = track;
}

@JsonProperty("info")
public Info getInfo() {
return info;
}

@JsonProperty("info")
public void setInfo(Info info) {
this.info = info;
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