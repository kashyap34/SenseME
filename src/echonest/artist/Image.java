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
"url",
"tags",
"width",
"height",
"aspect_ratio",
"verified",
"license"
})
public class Image {

@JsonProperty("url")
private String url;
@JsonProperty("tags")
private List<Object> tags = new ArrayList<Object>();
@JsonProperty("width")
private int width;
@JsonProperty("height")
private int height;
@JsonProperty("aspect_ratio")
private double aspect_ratio;
@JsonProperty("verified")
private boolean verified;
@JsonProperty("license")
private License license;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("tags")
public List<Object> getTags() {
return tags;
}

@JsonProperty("tags")
public void setTags(List<Object> tags) {
this.tags = tags;
}

@JsonProperty("width")
public int getWidth() {
	return width;
}

@JsonProperty("width")
public void setWidth(int width) {
	this.width = width;
}

@JsonProperty("height")
public int getHeight() {
	return height;
}

@JsonProperty("height")
public void setHeight(int height) {
	this.height = height;
}

@JsonProperty("aspect_ratio")
public double getAspect_ratio() {
	return aspect_ratio;
}

@JsonProperty("aspect_ratio")
public void setAspect_ratio(double aspect_ratio) {
	this.aspect_ratio = aspect_ratio;
}

@JsonProperty("verified")
public boolean isVerified() {
return verified;
}

@JsonProperty("verified")
public void setVerified(boolean verified) {
this.verified = verified;
}

@JsonProperty("license")
public License getLicense() {
return license;
}

@JsonProperty("license")
public void setLicense(License license) {
this.license = license;
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
