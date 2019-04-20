package com.agilemonkeys.crmapi.service;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.request.UserRequest;

public interface UserService {
    User getUserById(int id);
    User getUserByUsername(String username);
    User createUser(UserRequest userRequest);
}
