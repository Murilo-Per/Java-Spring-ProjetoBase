package br.com.muriloper.api.repository;

import br.com.muriloper.api.domain.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, Integer> {
    Optional<UserLogin> findByEmail(String email);
}
