package com.agilemonkeys.crmapi.utils;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.request.UserRequest;

public class Converter {
    public static User fromUserRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return user;
    }
}
