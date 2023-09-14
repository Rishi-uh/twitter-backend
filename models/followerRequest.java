package twitterproj.models;

import java.util.List;

public class followerRequest {
	
	private CustomUser follower;
	private List<CustomUser> followees;
	
	public followerRequest() {
		
	}
	
	public followerRequest(CustomUser follower, List<CustomUser> followees) {
		
	}
	public CustomUser getFollower() {
		return follower;
	}


	public void setFollower(CustomUser follower) {
		this.follower = follower;
	}


	public List<CustomUser> getFollowees() {
		return followees;
	}


	public void setFollowees(List<CustomUser> followees) {
		this.followees = followees;
	}


	@Override
	public String toString() {
		return "followerRequest [follower=" + follower + ", followees=" + followees + "]";
	}
}
