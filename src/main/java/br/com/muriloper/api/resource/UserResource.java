package br.com.muriloper.api.resource;

import br.com.muriloper.api.domain.UserLogin;
import br.com.muriloper.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserLogin> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(this.service.findById(id));
    }
}
