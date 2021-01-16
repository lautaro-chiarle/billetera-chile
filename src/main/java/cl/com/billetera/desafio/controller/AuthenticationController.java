package cl.com.billetera.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.com.billetera.desafio.model.AuthRequest;
import cl.com.billetera.desafio.model.AuthResponse;
import cl.com.billetera.desafio.repository.UserRepository;
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
	private UserRepository userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
		return userRepository.findByEmail(ar.getUsername()).map((userDetails) -> {
			String encoded = passwordEncoder.encode(ar.getPassword());
			if (encoded.equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

}