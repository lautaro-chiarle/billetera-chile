package cl.com.billetera.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.com.billetera.desafio.model.User;
import cl.com.billetera.desafio.repository.UserRepository;
import cl.com.billetera.desafio.util.PBKDF2Encoder;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class UserService {  


    @Autowired UserRepository userRepository;   


	@Autowired
	private PBKDF2Encoder passwordEncoder;

    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }    

    public Mono<User> save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }	    

    public Mono<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

	public Mono<User> setActive(String email, Boolean isActive) {
        return this.findByEmail(email).flatMap( user -> {
            user.setIsActive(isActive);
            return this.save(user);
        });
        
    }
    
	public Mono<User> setActive(User user, Boolean isActive) {
        user.setIsActive(isActive);
        return this.save(user);
	}    

	public Mono<Void> deleteById(Long id) {
		return userRepository.deleteById(id);
	}

	public Mono<User> create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.save(user);
	}

	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	public Mono<User> login(String username, String password) {
        return userRepository
            .findByEmail(username)
            .flatMap(u -> {
                String encoded = passwordEncoder.encode(password);
                if (encoded.equals(u.getPassword())) {
                    return this.setActive(u, true);
                }
                return Mono.empty();
            });
	}

    
}
