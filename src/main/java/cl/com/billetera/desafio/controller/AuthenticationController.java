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
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Autenticación", description = "Recurso para la gestión de Seguridad")
public class AuthenticationController {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userService;

	@SecurityRequirements
	@Operation(method = "POST", description = "Endpoint para autenticarse en el servicio.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!") })
	@PostMapping("/signin")
	public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {

		return userService.login(ar.getUsername(), ar.getPassword()).map((user) -> {
			return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken((User) user)));
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@SecurityRequirements
	@Operation(method = "POST", description = "Endpoint para dar de alta un usuario.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!") })
	@PostMapping("/signup")
	public Mono<User> signup(@RequestBody User user) {
		return userService.create(user);
	}

	@Operation(method = "POST", description = "Endpoint para terminar la sesión.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!"),
			@ApiResponse(responseCode = "401", description = "El usuario es incorrecto!"),
			@ApiResponse(responseCode = "403", description = "No tiene permisos para acceder al endpoint!") }) //
	@GetMapping("/signout")
	public Mono<ResponseEntity<User>> signout() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication userDetails = (Authentication) context.getAuthentication();
		String email = userDetails.getPrincipal().toString();
		return userService.setActive(email, false).map((inactiveUser) -> {
			SecurityContextHolder.clearContext();
			return ResponseEntity.ok(inactiveUser);
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

}