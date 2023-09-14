package twitterproj.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import twitterproj.security.*;
import twitterproj.models.Role;
import twitterproj.models.*;
import twitterproj.repos.TwitterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class AuthenticationService {
	
	private final TwitterRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	public AuthenticationService(TwitterRepository repository,PasswordEncoder passwordEncoder,JwtService jwtService,AuthenticationManager authenticationManager) {
		this.repository=repository;
		this.passwordEncoder=passwordEncoder;
		this .jwtService=jwtService;
		this.authenticationManager=authenticationManager;
	}
	
	public AuthenticationResponse register(RegisterRequest request) {
		
		if(request.getPassword()==null)
		{
			throw new IllegalArgumentException("Passwordcannotbenull");
					}
		var user = CustomUser.builder()
				.username(request.getUsername())
				.Password(passwordEncoder.encode(request.getPassword()))
				.Name(request.getName())
				.email(request.getEmail())
				.role(Role.USER)
				.build();
		repository.save((CustomUser) user);
		var JwtToken = jwtService.generateToken((UserDetails) user);
		return AuthenticationResponse.builder()
				.token(JwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()
						)
				);
		var user = repository.findByUsername(request.getUsername())
				.orElseThrow();
		var jwtToken = jwtService.generateToken((UserDetails) user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
}