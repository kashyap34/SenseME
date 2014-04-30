package lastfm.artist;

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
"listeners",
"playcount"
})
public class Stats {

@JsonProperty("listeners")
private String listeners;
@JsonProperty("playcount")
private String playcount;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("listeners")
public String getListeners() {
return listeners;
}

@JsonProperty("listeners")
public void setListeners(String listeners) {
this.listeners = listeners;
}

@JsonProperty("playcount")
public String getPlaycount() {
return playcount;
}

@JsonProperty("playcount")
public void setPlaycount(String playcount) {
this.playcount = playcount;
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