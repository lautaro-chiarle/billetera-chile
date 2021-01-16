package cl.com.billetera.desafio.controller;



import cl.com.billetera.desafio.repository.UserRepository;
import cl.com.billetera.desafio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired UserRepository userRepository;    

    @GetMapping("")
    public Flux<User> getHome() {

        return userRepository.findAll();

    }

    // @GetMapping("/{id}")
    // Mono<User> userById(@PathVariable Long id) {
    //     return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
    //             HttpStatus.NOT_FOUND));
    // }

    @PostMapping("")
    public Mono<User> postUser(@RequestBody User user) {

        return userRepository.save(user);
    }

    @PutMapping("")
    public Mono<User> updateUser(@RequestBody User user) {

        return userRepository.save(user);

    }

    @DeleteMapping("")
    public boolean deleteUser(@RequestBody User user) {

        try {
            userRepository.deleteById(user.getId()).block(); 
            return true;

        } catch (Exception e) {

            return false;
        }
    }
}