package com.agilemonkeys.crmapi.service.impl;

import com.agilemonkeys.crmapi.exception.ExceptionResponseError;
import com.agilemonkeys.crmapi.exception.ResourceNotFoundException;
import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;
import com.agilemonkeys.crmapi.model.request.CreateUserRequest;
import com.agilemonkeys.crmapi.repository.UserRepository;
import com.agilemonkeys.crmapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.agilemonkeys.crmapi.utils.Converter.fromCreateUserRequest;


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
    public User createUser(CreateUserRequest createUserRequest) {
        createUserRequest.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        User newUser = fromCreateUserRequest(createUserRequest);

        if ( createUserRequest.getRoles() == null || createUserRequest.getRoles().isEmpty() )
            newUser.getRoles().add(Roles.ROLE_USER);
        else
            newUser.setRoles(createUserRequest.getRoles());

        return userRepository.save(newUser);
    }
}
