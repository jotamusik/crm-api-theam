package com.agilemonkeys.crmapi.service;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.request.CreateUserRequest;
import com.agilemonkeys.crmapi.model.request.UpdateUserRequest;

public interface UserService {
    User getUserById(int id);
    User getUserByUsername(String username);
    boolean existsUserWithUsername(String username);
    User createUser(CreateUserRequest createUserRequest);
    User updateUser(int id, UpdateUserRequest updateUserRequest);
}
