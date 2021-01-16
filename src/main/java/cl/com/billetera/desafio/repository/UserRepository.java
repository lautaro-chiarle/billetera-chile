package cl.com.billetera.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.com.billetera.desafio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}