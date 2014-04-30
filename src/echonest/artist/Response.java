package echonest.artist;

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
"status",
"start",
"total",
"images"
})
public class Response {

@JsonProperty("status")
private Status status;
@JsonProperty("start")
private long start;
@JsonProperty("total")
private long total;
@JsonProperty("images")
private List<Image> images = new ArrayList<Image>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("status")
public Status getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(Status status) {
this.status = status;
}

@JsonProperty("start")
public long getStart() {
return start;
}

@JsonProperty("start")
public void setStart(long start) {
this.start = start;
}

@JsonProperty("total")
public long getTotal() {
return total;
}

@JsonProperty("total")
public void setTotal(long total) {
this.total = total;
}

@JsonProperty("images")
public List<Image> getImages() {
return images;
}

@JsonProperty("images")
public void setImages(List<Image> images) {
this.images = images;
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
