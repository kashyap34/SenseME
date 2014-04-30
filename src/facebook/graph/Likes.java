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
"can_like",
"user_likes"
})
public class Likes {

@JsonProperty("count")
private int count;
@JsonProperty("can_like")
private boolean can_like;
@JsonProperty("user_likes")
private boolean user_likes;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("count")
public int getCount() {
return count;
}

@JsonProperty("count")
public void setCount(int count) {
this.count = count;
}

@JsonProperty("can_like")
public boolean isCan_like() {
return can_like;
}

@JsonProperty("can_like")
public void setCan_like(boolean can_like) {
this.can_like = can_like;
}

@JsonProperty("user_likes")
public boolean isUser_likes() {
return user_likes;
}

@JsonProperty("user_likes")
public void setUser_likes(boolean user_likes) {
this.user_likes = user_likes;
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
