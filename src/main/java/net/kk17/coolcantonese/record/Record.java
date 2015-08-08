package net.kk17.coolcantonese.record;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("records") 
@Indexes({
	@Index(fields = @Field("userId")),
	@Index(fields = @Field("type"))
})
public class Record implements Serializable{
	private static final long serialVersionUID = 6052153204558377775L;
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	@JsonProperty("uid")
	@Property("uid")
	private String userId;
	private int type = Type.TRANSLATION_REQUEST;
    @NotNull
    @Size(min = 1, max = 1000)
	private String content;
	private Date time;
	@Reference
	private Record reply;



	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Record getReply() {
		return reply;
	}

	public void setReply(Record reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", userId=" + userId + ", type=" + type + ", content=" + content + ", time=" + time
				+ ", reply=" + reply + "]";
	}

	public static final class Type{
		private Type(){}
		public static final int TRANSLATION_REQUEST = 0;
		public static final int TRANSLATION_RESULT = 1;
		public static final int CHAT_REQUEST = 2;
		public static final int CHAT_RESPONSE = 3;

	}

}
