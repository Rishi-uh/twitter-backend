package twitterproj.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import twitterproj.models.*;

@Repository
public interface TwitterRepository extends JpaRepository<CustomUser, Long> {
	  Optional<CustomUser> findByUsername (String username);
	}