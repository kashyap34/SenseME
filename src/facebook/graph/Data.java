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
"id",
"from",
"start_time",
"end_time",
"publish_time",
"application",
"musicdata",
"type",
"no_feed_story",
"likes",
"comments"
})
public class Data {

@JsonProperty("id")
private String id;
@JsonProperty("from")
private From from;
@JsonProperty("start_time")
private String start_time;
@JsonProperty("end_time")
private String end_time;
@JsonProperty("publish_time")
private String publish_time;
@JsonProperty("application")
private Application application;
@JsonProperty("musicdata")
private Musicdata musicdata;
@JsonProperty("type")
private String type;
@JsonProperty("no_feed_story")
private boolean no_feed_story;
@JsonProperty("likes")
private Likes likes;
@JsonProperty("comments")
private Comments comments;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("from")
public From getFrom() {
return from;
}

@JsonProperty("from")
public void setFrom(From from) {
this.from = from;
}

@JsonProperty("start_time")
public String getStart_time() {
return start_time;
}

@JsonProperty("start_time")
public void setStart_time(String start_time) {
this.start_time = start_time;
}

@JsonProperty("end_time")
public String getEnd_time() {
return end_time;
}

@JsonProperty("end_time")
public void setEnd_time(String end_time) {
this.end_time = end_time;
}

@JsonProperty("publish_time")
public String getPublish_time() {
return publish_time;
}

@JsonProperty("publish_time")
public void setPublish_time(String publish_time) {
this.publish_time = publish_time;
}

@JsonProperty("application")
public Application getApplication() {
return application;
}

@JsonProperty("application")
public void setApplication(Application application) {
this.application = application;
}

@JsonProperty("musicdata")
public Musicdata getMusicdata() {
return musicdata;
}

@JsonProperty("musicdata")
public void setMusicdata(Musicdata musicdata) {
this.musicdata = musicdata;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("no_feed_story")
public boolean isNo_feed_story() {
return no_feed_story;
}

@JsonProperty("no_feed_story")
public void setNo_feed_story(boolean no_feed_story) {
this.no_feed_story = no_feed_story;
}

@JsonProperty("likes")
public Likes getLikes() {
return likes;
}

@JsonProperty("likes")
public void setLikes(Likes likes) {
this.likes = likes;
}

@JsonProperty("comments")
public Comments getComments() {
return comments;
}

@JsonProperty("comments")
public void setComments(Comments comments) {
this.comments = comments;
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
