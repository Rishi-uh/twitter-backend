package twitterproj.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tweets")
public class tweets {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
@ManyToOne
@JoinColumn(name="user_id_column",referencedColumnName="id")
private CustomUser user;
@Column(name="content")
private String tweet;

@CreationTimestamp
@Column(name="created_at")
private LocalDateTime createdat;

public tweets() {}
	public tweets(CustomUser user, String tweet) {
	
	this.user=user;
	this.tweet=tweet;
	
}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public CustomUser getUser() {
		return user;
	}
	public void setUser(CustomUser user) {
		this.user= user;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public LocalDateTime getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	@Override
	public String toString() {
		return "tweets [id=" + id + ", user=" + user + ", tweet=" + tweet + ", createdat=" + createdat + "]";
	}
	
}