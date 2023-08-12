package br.com.muriloper.api.service;

import br.com.muriloper.api.domain.UserLogin;

public interface UserService {
    UserLogin findById(Integer id);
}
