package cl.com.billetera.desafio.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import cl.com.billetera.desafio.model.User;

//@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}

