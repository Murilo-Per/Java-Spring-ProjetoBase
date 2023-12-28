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

import static br.com.muriloper.api.Utils.ApiMessages.EMAIL_EXISTS;
import static br.com.muriloper.api.Utils.ApiMessages.OBJECT_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserLogin findById(Integer id) {
        Optional<UserLogin> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND.getMensagem()));
    }

    @Override
    public UserLogin create(UserDTO obj) {
        Optional<UserLogin> user = findByEmail(obj.getEmail());

        if ( user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegrityViolationException(EMAIL_EXISTS.getMensagem());
        } else {
            return repository.save(mapper.map(obj, UserLogin.class));
        }
    }

    public Optional<UserLogin> findByEmail(String email){
        return repository.findByEmail(email);
    }

    @Override
    public UserLogin update(UserDTO obj) {
        Optional<UserLogin> user = findByEmail(obj.getEmail());

        if ( user.isPresent() && user.get().getId().equals(obj.getId())) {
            return repository.save(mapper.map(obj, UserLogin.class));
        } else throw new DataIntegrityViolationException(OBJECT_NOT_FOUND.getMensagem());
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public Boolean login(UserDTO obj) {
        Optional<UserLogin> user = findByEmail(obj.getEmail());

        if (user.isEmpty() || !user.get().getEmail().equals(obj.getEmail())
           || !user.get().getPassword().equals(obj.getPassword())) {
            throw new DataIntegrityViolationException(OBJECT_NOT_FOUND.getMensagem());
        } else {
            return true;
        }
    }


}
