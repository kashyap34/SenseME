package facebook.graph;

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
"data",
"paging"
})
public class FacebookMusicGraph {

@JsonProperty("data")
private List<Data> data = new ArrayList<Data>();
@JsonProperty("paging")
private Paging paging;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("data")
public List<Data> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<Data> data) {
this.data = data;
}

@JsonProperty("paging")
public Paging getPaging() {
return paging;
}

@JsonProperty("paging")
public void setPaging(Paging paging) {
this.paging = paging;
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
