package lastfm.artist;

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
"name",
"mbid",
"bandmembers",
"url",
"image",
"streamable",
"ontour",
"stats"
})
public class Artist {

@JsonProperty("name")
private String name;
@JsonProperty("mbid")
private String mbid;
@JsonProperty("bandmembers")
private Bandmembers bandmembers;
@JsonProperty("url")
private String url;
@JsonProperty("image")
private List<Image> image = new ArrayList<Image>();
@JsonProperty("streamable")
private String streamable;
@JsonProperty("ontour")
private String ontour;
@JsonProperty("stats")
private Stats stats;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("mbid")
public String getMbid() {
return mbid;
}

@JsonProperty("mbid")
public void setMbid(String mbid) {
this.mbid = mbid;
}

@JsonProperty("bandmembers")
public Bandmembers getBandmembers() {
return bandmembers;
}

@JsonProperty("bandmembers")
public void setBandmembers(Bandmembers bandmembers) {
this.bandmembers = bandmembers;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("image")
public List<Image> getImage() {
return image;
}

@JsonProperty("image")
public void setImage(List<Image> image) {
this.image = image;
}

@JsonProperty("streamable")
public String getStreamable() {
return streamable;
}

@JsonProperty("streamable")
public void setStreamable(String streamable) {
this.streamable = streamable;
}

@JsonProperty("ontour")
public String getOntour() {
return ontour;
}

@JsonProperty("ontour")
public void setOntour(String ontour) {
this.ontour = ontour;
}

@JsonProperty("stats")
public Stats getStats() {
return stats;
}

@JsonProperty("stats")
public void setStats(Stats stats) {
this.stats = stats;
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
