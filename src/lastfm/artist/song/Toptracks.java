package lastfm.artist.song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
"@attr"
})
public class Toptracks {

@JsonProperty("track")
private List<Track> track = new ArrayList<Track>();
@JsonProperty("@attr")
private _attr_ _attr;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("track")
public List<Track> getTrack() {
return track;
}

@JsonProperty("track")
public void setTrack(List<Track> track) {
this.track = track;
}

@JsonProperty("@attr")
public _attr_ get_attr() {
return _attr;
}

@JsonProperty("@attr")
public void set_attr(_attr_ _attr) {
this._attr = _attr;
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