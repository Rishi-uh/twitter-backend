package twitterproj.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import twitterproj.models.likes;
import twitterproj.models.tweets;
import twitterproj.models.CustomUser;

public interface LikesRepository extends JpaRepository<likes, Long> {

	 
	  List<likes> findByUser(CustomUser user);
	  
	  List<likes> findByTweet(tweets tweet);
	  
	  List<likes> deleteByTweet(tweets tweet);
	  
	}