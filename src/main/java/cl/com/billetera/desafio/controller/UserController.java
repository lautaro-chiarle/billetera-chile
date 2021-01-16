package cl.com.billetera.desafio.controller;


import cl.com.billetera.desafio.model.User;
import cl.com.billetera.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/all")
    Iterable<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User userById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user/save")
    User save(@RequestBody User user) {
        return userRepository.save(user);
    }

}