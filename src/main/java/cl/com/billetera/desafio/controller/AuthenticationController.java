package cl.com.billetera.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.com.billetera.desafio.dto.AuthRequest;
import cl.com.billetera.desafio.dto.AuthResponse;
import cl.com.billetera.desafio.model.User;
import cl.com.billetera.desafio.service.UserService;
import cl.com.billetera.desafio.util.JWTUtil;
import cl.com.billetera.desafio.util.PBKDF2Encoder;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {

		return userService.login(ar.getUsername(),ar.getPassword() ).map((user) -> {
			return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken((User)user)));
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@PostMapping("/signup")
    public Mono<User> signup(@RequestBody User user) {
        return userService.create(user);
	}	
	
	@GetMapping("/signout")
    public Mono<ResponseEntity<User>> signout() {
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication userDetails = (Authentication) context.getAuthentication();
        String email = userDetails.getPrincipal().toString();
		return userService.setActive(email, false)
			.map((inactiveUser) -> {
				SecurityContextHolder.clearContext();
				return 	ResponseEntity.ok(inactiveUser );
			})
			.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	
	
    }		

}