package com.agilemonkeys.crmapi.service.impl;

import com.agilemonkeys.crmapi.exception.ExceptionResponseError;
import com.agilemonkeys.crmapi.exception.ResourceNotFoundException;
import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;
import com.agilemonkeys.crmapi.model.request.UserRequest;
import com.agilemonkeys.crmapi.repository.UserRepository;
import com.agilemonkeys.crmapi.service.UserService;
import com.agilemonkeys.crmapi.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if ( optionalUser.isPresent() )
            return optionalUser.get();
        else
            throw new ResourceNotFoundException(new ExceptionResponseError("user", "not_exists"));
    }

    @Override
    public User getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if ( optionalUser.isPresent() )
            return  optionalUser.get();
        else
            throw new ResourceNotFoundException(new ExceptionResponseError("user", "not_exists"));
    }

    @Override
    public User createUser(UserRequest userRequest) {
        userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        User user = Converter.fromUserRequestToUser(userRequest);

        if ( userRequest.getRoles() == null || userRequest.getRoles().isEmpty() ) {
            user.getRoles().add(Roles.ROLE_USER);
        }
        else {
            user.setRoles(userRequest.getRoles());
        }
        return userRepository.save(user);
    }
}
