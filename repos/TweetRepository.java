package twitterproj.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import twitterproj.models.tweets;
import twitterproj.models.CustomUser;


@Repository
public interface TweetRepository extends JpaRepository<tweets, Long> {

	 
	  List<tweets> findByUser(CustomUser user);
	  
	}