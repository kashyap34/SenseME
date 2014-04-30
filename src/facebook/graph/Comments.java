package facebook.graph;

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
"count",
"can_comment",
"comment_order"
})
public class Comments {

@JsonProperty("count")
private int count;
@JsonProperty("can_comment")
private boolean can_comment;
@JsonProperty("comment_order")
private String comment_order;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("count")
public int getCount() {
return count;
}

@JsonProperty("count")
public void setCount(int count) {
this.count = count;
}

@JsonProperty("can_comment")
public boolean isCan_comment() {
return can_comment;
}

@JsonProperty("can_comment")
public void setCan_comment(boolean can_comment) {
this.can_comment = can_comment;
}

@JsonProperty("comment_order")
public String getComment_order() {
return comment_order;
}

@JsonProperty("comment_order")
public void setComment_order(String comment_order) {
this.comment_order = comment_order;
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
