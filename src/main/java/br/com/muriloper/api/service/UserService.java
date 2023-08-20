package br.com.muriloper.api.service;

import br.com.muriloper.api.domain.UserLogin;
import br.com.muriloper.api.domain.dto.UserDTO;

public interface UserService {
    UserLogin findById(Integer id);

    UserLogin create(UserDTO obj);

    UserLogin update(UserDTO obj);

    void delete(Integer id);
}
