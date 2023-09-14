package twitterproj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitterproj.models.CustomUser;

@RestController
@RequestMapping("/jwt/api/demo")
public class DemoController {
	
	@GetMapping
	public ResponseEntity<String> sayHello()
	{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    CustomUser user = (CustomUser) authentication.getPrincipal();
	    return ResponseEntity.ok("Hello "+ user.getUsername());
	}
}
