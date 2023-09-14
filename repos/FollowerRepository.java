package twitterproj.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import twitterproj.models.follower;
import twitterproj.models.CustomUser;

public interface FollowerRepository extends JpaRepository<follower, Long> {

	List<follower> findByFollower(CustomUser follower);

	void deleteByFollower(CustomUser follower);
	  
	}