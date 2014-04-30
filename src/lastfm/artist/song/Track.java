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
"name",
"duration",
"playcount",
"listeners",
"mbid",
"url",
"streamable",
"artist",
"image",
"@attr"
})
public class Track {

@JsonProperty("name")
private String name;
@JsonProperty("duration")
private String duration;
@JsonProperty("playcount")
private String playcount;
@JsonProperty("listeners")
private String listeners;
@JsonProperty("mbid")
private String mbid;
@JsonProperty("url")
private String url;
@JsonProperty("streamable")
private Streamable streamable;
@JsonProperty("artist")
private Artist artist;
@JsonProperty("image")
private List<Image> image = new ArrayList<Image>();
@JsonProperty("@attr")
private lastfm.artist.song._attr _attr;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("duration")
public String getDuration() {
return duration;
}

@JsonProperty("duration")
public void setDuration(String duration) {
this.duration = duration;
}

@JsonProperty("playcount")
public String getPlaycount() {
return playcount;
}

@JsonProperty("playcount")
public void setPlaycount(String playcount) {
this.playcount = playcount;
}

@JsonProperty("listeners")
public String getListeners() {
return listeners;
}

@JsonProperty("listeners")
public void setListeners(String listeners) {
this.listeners = listeners;
}

@JsonProperty("mbid")
public String getMbid() {
return mbid;
}

@JsonProperty("mbid")
public void setMbid(String mbid) {
this.mbid = mbid;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("streamable")
public Streamable getStreamable() {
return streamable;
}

@JsonProperty("streamable")
public void setStreamable(Streamable streamable) {
this.streamable = streamable;
}

@JsonProperty("artist")
public Artist getArtist() {
return artist;
}

@JsonProperty("artist")
public void setArtist(Artist artist) {
this.artist = artist;
}

@JsonProperty("image")
public List<Image> getImage() {
return image;
}

@JsonProperty("image")
public void setImage(List<Image> image) {
this.image = image;
}

@JsonProperty("@attr")
public lastfm.artist.song._attr get_attr() {
return _attr;
}

@JsonProperty("@attr")
public void set_attr(lastfm.artist.song._attr _attr) {
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