package twitterproj.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitterproj.models.CustomUser;
import twitterproj.models.follower;
import twitterproj.models.likes;
import twitterproj.models.tweets;
import twitterproj.models.repost;
import twitterproj.repos.FollowerRepository;
import twitterproj.repos.LikesRepository;
import twitterproj.repos.RepostRepository;
import twitterproj.repos.TweetRepository;
import twitterproj.repos.TwitterRepository;

@RestController
@RequestMapping("/twitter")
public class TwitterController {
	
	public CustomUser username() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (CustomUser) authentication.getPrincipal();
    }
		
	private final TwitterRepository userRepo;
	private final TweetRepository tweetRepo;
	private final FollowerRepository followerRepo;
	private final LikesRepository likesRepo;
	private final RepostRepository repostRepo;
	
	@Autowired
	public TwitterController(TwitterRepository userRepo, TweetRepository tweetRepo,FollowerRepository followerRepo,LikesRepository likesRepo,RepostRepository repostRepo)
	{
		this.userRepo=userRepo;
		this.tweetRepo = tweetRepo;
		this.followerRepo=followerRepo;
		this.repostRepo=repostRepo;
		this.likesRepo=likesRepo;
	}
	
	@GetMapping("/home") //shows tweets from the users that our authenticated user follows
	public ResponseEntity<List<tweets>> getTweets()
	{
		try {
		CustomUser user = username();
		List<tweets> tweets = new ArrayList<tweets>();
		List<follower> follows = followerRepo.findByFollower(user);
	    for(follower follows_ : follows)
	    {
	    	CustomUser hey = follows_.getFollowee();
	    	List<tweets> userTweets = tweetRepo.findByUser(hey);
	    	List<tweets> userReposts = repostRepo.findByUser_id(hey);
	    	tweets.addAll(userTweets);
	    	tweets.addAll(userReposts);
	    }
	    
	    return new ResponseEntity<>(tweets, HttpStatus.OK);
	    }
		catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/tweet") //lets an authenticated user post a tweet
	public ResponseEntity<tweets> tweet(@RequestBody tweets tweet){
		try {
		CustomUser user = username();
		tweet.setUser(user);
		tweetRepo.save(tweet);
		return new ResponseEntity<>(tweet,HttpStatus.OK);
		}
		catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/like") //lets an authenticated user like a tweet
	public ResponseEntity<likes> like(@RequestBody tweets tweet)
	{
		try {
			CustomUser user = username();
			likes like = new likes();
			like.setUser(user);
			tweets _tweet = tweetRepo.findById(tweet.getId()).orElseThrow();
			like.setTweet(_tweet);
			likesRepo.save(like);
			return new ResponseEntity<>(like, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@PostMapping("/repost") //lets an authenticated user repost a tweet
	public ResponseEntity<repost> repost(@RequestBody tweets tweet)
	{
		try {
			CustomUser user = username();
			repost repost = new repost();
			repost.setUser(user);
			tweets _tweet = tweetRepo.findById(tweet.getId()).orElseThrow();
			repost.setTweet_id(_tweet);
			repostRepo.save(repost);
			return new ResponseEntity<>(repost, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	}
	
	@PostMapping("/follow") //lets an authenticated user follow another user
	public ResponseEntity<follower> follow(@RequestBody CustomUser followee)
	{
		try {
			CustomUser user = username();
			follower follower = new follower();
			follower.setFollower(user);
			CustomUser _followee = userRepo.findByUsername(followee.getUsername()).orElseThrow();
			follower.setFollowee(_followee);
			followerRepo.save(follower);
			return new ResponseEntity<>(follower,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	}
	
	@DeleteMapping("/unfollow")
	public ResponseEntity<String> unfollow(@RequestBody CustomUser followee)
	{
		try {
			CustomUser user = username();
			CustomUser followee_ = userRepo.findByUsername(followee.getUsername()).orElseThrow();
			List<follower> following = followerRepo.findByFollower(user);
			for(follower following_ : following) {
				if(following_.getFollowee()==followee_) {
					followerRepo.delete(following_);
					return  new ResponseEntity<>("Successfully unfollowed " + followee_.getName(), HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@DeleteMapping("/unlike")
	public ResponseEntity<String> unlike(@RequestBody tweets tweet){
		try {
			CustomUser user = username();
			List<likes> likes = likesRepo.findByUser(user);
			//tweets tweet_ = tweetRepo.findById(tweet.getId()).orElseThrow();
			//List<likes> likes = likesRepo.findByTweet(tweet_);
			for(likes like : likes) {
				if(like.getTweet().getId()==tweet.getId()) {
					likesRepo.deleteById(like.getId());
					return  new ResponseEntity<>("Successfully unliked", HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody tweets tweet){
		try {
			CustomUser user = username();
			tweets tweet_ = tweetRepo.findById(tweet.getId()).orElseThrow();
			if(tweet_.getUser()==user) {
			tweetRepo.delete(tweet_);
			return new ResponseEntity<>("successfully deleted the tweet",HttpStatus.OK);
					}
			return new ResponseEntity<>("bad request",HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
}
