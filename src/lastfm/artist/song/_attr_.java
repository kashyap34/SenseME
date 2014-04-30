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
"artist",
"page",
"perPage",
"totalPages",
"total"
})
public class _attr_ {

@JsonProperty("artist")
private String artist;
@JsonProperty("page")
private String page;
@JsonProperty("perPage")
private String perPage;
@JsonProperty("totalPages")
private String totalPages;
@JsonProperty("total")
private String total;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("artist")
public String getArtist() {
return artist;
}

@JsonProperty("artist")
public void setArtist(String artist) {
this.artist = artist;
}

@JsonProperty("page")
public String getPage() {
return page;
}

@JsonProperty("page")
public void setPage(String page) {
this.page = page;
}

@JsonProperty("perPage")
public String getPerPage() {
return perPage;
}

@JsonProperty("perPage")
public void setPerPage(String perPage) {
this.perPage = perPage;
}

@JsonProperty("totalPages")
public String getTotalPages() {
return totalPages;
}

@JsonProperty("totalPages")
public void setTotalPages(String totalPages) {
this.totalPages = totalPages;
}

@JsonProperty("total")
public String getTotal() {
return total;
}

@JsonProperty("total")
public void setTotal(String total) {
this.total = total;
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