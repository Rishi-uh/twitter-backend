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
@Table(name="Reposts")
public class repost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id_column",referencedColumnName="id")
	private CustomUser user;
	
	@ManyToOne
	@JoinColumn(name="tweet_id_column",referencedColumnName="id")
	private tweets tweet;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdat;
	
	public repost() {
		
	}
	public repost(CustomUser user, tweets tweet) {
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
		this.user = user;
	}
	public tweets getTweet() {
		return tweet;
	}
	public void setTweet_id(tweets tweet) {
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
		return "repost [id=" + id + ", user=" + user + ", tweet=" + tweet + ", createdat=" + createdat
				+ "]";
	}
}
