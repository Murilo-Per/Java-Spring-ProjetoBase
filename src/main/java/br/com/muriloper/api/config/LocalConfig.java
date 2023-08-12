package br.com.muriloper.api.config;

import br.com.muriloper.api.domain.UserLogin;
import br.com.muriloper.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        UserLogin u1 = new UserLogin(null, "Usuario1", "usuario1@gmail.com","1234");
        UserLogin u2 = new UserLogin(null, "Usuario2", "usuario2@gmail.com","4321");

        this.repository.saveAll(List.of(u1,u2));
    }
}
