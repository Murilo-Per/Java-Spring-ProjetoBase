package br.com.muriloper.api.service.impl;

import br.com.muriloper.api.domain.UserLogin;
import br.com.muriloper.api.repository.UserRepository;
import br.com.muriloper.api.service.UserService;
import br.com.muriloper.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserLogin findById(Integer id) {
        Optional<UserLogin> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }
}
