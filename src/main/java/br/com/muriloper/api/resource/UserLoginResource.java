package br.com.muriloper.api.resource;

import br.com.muriloper.api.domain.dto.UserDTO;
import br.com.muriloper.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/login")
public class UserLoginResource {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO req){
        if (service.login(req)) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().replacePath("/{home}")
                    .buildAndExpand("home").toUri();

            return ResponseEntity.created(uri).build();
        } else return ResponseEntity.notFound().build();
    }

}
