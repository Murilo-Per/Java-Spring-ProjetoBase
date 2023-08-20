package br.com.muriloper.api.service.impl;

import br.com.muriloper.api.domain.UserLogin;
import br.com.muriloper.api.domain.dto.UserDTO;
import br.com.muriloper.api.repository.UserRepository;
import br.com.muriloper.api.service.UserService;
import br.com.muriloper.api.service.exception.DataIntegrityViolationException;
import br.com.muriloper.api.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserLogin findById(Integer id) {
        Optional<UserLogin> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    @Override
    public UserLogin create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, UserLogin.class));
    }

    private void findByEmail(UserDTO obj){
        Optional<UserLogin> user = repository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado!");
        }
    }

    @Override
    public UserLogin update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, UserLogin.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
