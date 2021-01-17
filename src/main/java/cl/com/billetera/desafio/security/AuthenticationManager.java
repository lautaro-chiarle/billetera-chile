package cl.com.billetera.desafio.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cl.com.billetera.desafio.service.UserService;
import cl.com.billetera.desafio.util.JWTUtil;
import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	UserService userService;

	@Override
	@SuppressWarnings("unchecked")
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();

		try {

			// Check Token expiration
			if (!jwtUtil.validateToken(authToken)) {
				return Mono.empty();
			}

			Claims claims = jwtUtil.getAllClaimsFromToken(authToken);

			// Check Token was not signed out

			String email = claims.get("sub", String.class);
			return userService.findByEmail(email).flatMap( u -> {
				if (!u.getIsActive()) {
					return Mono.empty();
				} else {
					// Check roles
					List<String> rolesMap = claims.get("role", List.class);
					List<GrantedAuthority> authorities = new ArrayList<>();
					for (String rolemap : rolesMap) {
						authorities.add(new SimpleGrantedAuthority(rolemap));
					}
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
					SecurityContextHolder.getContext().setAuthentication(auth);
					return Mono.just(auth);
				}
			});

			

		} catch (Exception e) {
			return Mono.empty();
		}
	}
}