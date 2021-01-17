package cl.com.billetera.desafio.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cl.com.billetera.desafio.model.User;
import cl.com.billetera.desafio.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/user")
public class UserController {
 
    @Autowired UserService userService;  

    @GetMapping("")
    public Flux<User> getAll() {

        return userService.findAll();

    }

    @GetMapping("/{id}")
    Mono<User> userById(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PutMapping("")
    public Mono<User> updateUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("")
    public boolean deleteUser(@RequestBody User user) {

        try {
            userService.deleteById(user.getId()).block(); 
            return true;

        } catch (Exception e) {

            return false;
        }
    }
}