package twitterproj.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import twitterproj.models.repost;
import twitterproj.models.tweets;
import twitterproj.models.CustomUser;

public interface RepostRepository extends JpaRepository<repost, Long> {

	 
	  List<tweets> findByUser_id(CustomUser user);
	  
	  List<repost> findByTweet_id(tweets tweet);
	  
	  List<repost> deleteByTweet_id(tweets tweet);
	  
	}