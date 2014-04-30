package spotify.track;

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
"available",
"album",
"name",
"popularity",
"external-ids",
"length",
"href",
"artists",
"availability",
"track-number"
})
public class Track {

@JsonProperty("available")
private boolean available;
@JsonProperty("album")
private Album album;
@JsonProperty("name")
private String name;
@JsonProperty("popularity")
private String popularity;
@JsonProperty("external-ids")
private List<External_ids> external_ids = new ArrayList<External_ids>();
@JsonProperty("explicit")
private boolean explicit;
@JsonProperty("length")
private double length;
@JsonProperty("href")
private String href;
@JsonProperty("artists")
private List<Artist> artists = new ArrayList<Artist>();
@JsonProperty("availability")
private Availability availability;
@JsonProperty("track-number")
private String track_number;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("available")
public boolean isAvailable() {
return available;
}

@JsonProperty("available")
public void setAvailable(boolean available) {
this.available = available;
}

@JsonProperty("album")
public Album getAlbum() {
return album;
}

@JsonProperty("album")
public void setAlbum(Album album) {
this.album = album;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("popularity")
public String getPopularity() {
return popularity;
}

@JsonProperty("popularity")
public void setPopularity(String popularity) {
this.popularity = popularity;
}

@JsonProperty("external-ids")
public List<External_ids> getExternal_ids() {
return external_ids;
}

@JsonProperty("external-ids")
public void setExternal_ids(List<External_ids> external_ids) {
this.external_ids = external_ids;
}

@JsonProperty("explicit")
public boolean getExplicit() {
return explicit;
}

@JsonProperty("explicit")
public void setExplicit(boolean explicit) {
this.explicit = explicit;
}

@JsonProperty("length")
public double getLength() {
return length;
}

@JsonProperty("length")
public void setLength(double length) {
this.length = length;
}

@JsonProperty("href")
public String getHref() {
return href;
}

@JsonProperty("href")
public void setHref(String href) {
this.href = href;
}

@JsonProperty("artists")
public List<Artist> getArtists() {
return artists;
}

@JsonProperty("artists")
public void setArtists(List<Artist> artists) {
this.artists = artists;
}

@JsonProperty("availability")
public Availability getAvailability() {
return availability;
}

@JsonProperty("availability")
public void setAvailability(Availability availability) {
this.availability = availability;
}

@JsonProperty("track-number")
public String getTrack_number() {
return track_number;
}

@JsonProperty("track-number")
public void setTrack_number(String track_number) {
this.track_number = track_number;
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