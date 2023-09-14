package twitterproj.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="follower_table")
public class follower{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="follower_id", referencedColumnName="id")
	private CustomUser follower;
	
	@ManyToOne
	@JoinColumn(name="followee_id", referencedColumnName="id")
	private CustomUser followee;
	
	public follower() {
		
	}
	public follower(CustomUser follower, CustomUser followee) {
		this.follower=follower;
		this.followee=followee;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CustomUser getFollower() {
		return follower;
	}
	public void setFollower(CustomUser follower) {
		this.follower = follower;
	}
	public CustomUser getFollowee() {
		return followee;
	}
	public void setFollowee(CustomUser followee) {
		this.followee = followee;
	}
	@Override
	public String toString() {
		return "follower [id=" + id + ", follower=" + follower + ", followee=" + followee + "]";
	}
}
