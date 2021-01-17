package cl.com.billetera.desafio.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.com.billetera.desafio.exception.NotFoundException;
import cl.com.billetera.desafio.model.User;
import cl.com.billetera.desafio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Tag(name = "Usuarios", description = "Recurso para la gestión de usuarios")
@RestController
@RequestMapping(value = "/user")
public class UserController {
 
    @Autowired UserService userService;  

    @GetMapping("")
    public Flux<User> getAll() {

        return userService.findAll();

    }

    @Operation(method = "GET", description = "Endpoint para recuperar un usuario individualmente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!"),
			@ApiResponse(responseCode = "401", description = "El usuario es incorrecto!"),
			@ApiResponse(responseCode = "403", description = "No tiene permisos para acceder al endpoint!") }) //   
    @GetMapping("/{id}")
    Mono<User> userById(@PathVariable Long id) {
        Mono<User> fallback = Mono.error(new NotFoundException("No user account was found with id: " + id));
        return userService.findById(id).switchIfEmpty(fallback);
    }

    @Operation(method = "GET", description = "Endpoint para actualizar los datos de un usuario individualmente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!"),
			@ApiResponse(responseCode = "401", description = "El usuario es incorrecto!"),
			@ApiResponse(responseCode = "403", description = "No tiene permisos para acceder al endpoint!") }) //
   
    @PutMapping("")
    public Mono<User> updateUser(@RequestBody User user) {
        return userService.save(user);
    }

    @Operation(method = "GET", description = "Endpoint para eliminar un usuario individualmente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!"),
			@ApiResponse(responseCode = "401", description = "El usuario es incorrecto!"),
			@ApiResponse(responseCode = "403", description = "No tiene permisos para acceder al endpoint!") }) //
   
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {

       return userService.deleteById(id); 

    }
}