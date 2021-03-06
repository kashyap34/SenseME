package lastfm.artist.song;

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
"#text",
"fulltrack"
})
public class Streamable {

@JsonProperty("#text")
private String _text;
@JsonProperty("fulltrack")
private String fulltrack;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("#text")
public String get_text() {
return _text;
}

@JsonProperty("#text")
public void set_text(String _text) {
this._text = _text;
}

@JsonProperty("fulltrack")
public String getFulltrack() {
return fulltrack;
}

@JsonProperty("fulltrack")
public void setFulltrack(String fulltrack) {
this.fulltrack = fulltrack;
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