package com.agilemonkeys.crmapi.service.impl;

import com.agilemonkeys.crmapi.exception.ExceptionResponseError;
import com.agilemonkeys.crmapi.exception.ForbiddenException;
import com.agilemonkeys.crmapi.exception.ResourceNotFoundException;
import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;
import com.agilemonkeys.crmapi.model.request.CreateUserRequest;
import com.agilemonkeys.crmapi.model.request.UpdateUserRequest;
import com.agilemonkeys.crmapi.repository.UserRepository;
import com.agilemonkeys.crmapi.service.UserSecurityService;
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

    @Autowired
    UserSecurityService userSecurityService;

    @Override
    public User getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if ( optionalUser.isPresent() )
            return  optionalUser.get();
        else
            throw new ResourceNotFoundException(new ExceptionResponseError("user", "not_exists"));
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if ( optionalUser.isPresent() )
            return optionalUser.get();
        else
            throw new ResourceNotFoundException(new ExceptionResponseError("user", "not_exists"));
    }

    @Override
    public boolean existsUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
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

    @Override
    public User updateUser(int id, UpdateUserRequest updateUserRequest) {
        User user = getUserById(id);
        updateUserInfo(updateUserRequest, user);
        return userRepository.save(user);
    }

    private void updateUserInfo(UpdateUserRequest updateUserRequest, User user) {

        if ( updateUserRequest.getRoles() != null )
            if ( !updateUserRequest.getRoles().isEmpty() )
                if ( userSecurityService.isCurrentUser(Roles.ROLE_ADMIN) )
                    user.setRoles(updateUserRequest.getRoles());
                else
                    throw new ForbiddenException(new ExceptionResponseError("user_role", "action_not_allowed"));

        if ( updateUserRequest.getPassword() != null )
            if ( !updateUserRequest.getPassword().isEmpty() )
                if ( isCurrentUserAdminOrSelfUser(user) )
                    user.setPassword(bCryptPasswordEncoder.encode(updateUserRequest.getPassword()));
                else
                    throw new ForbiddenException(new ExceptionResponseError("user", "action_not_allowed"));
    }

    private boolean isCurrentUserAdminOrSelfUser(User user) {
        User currentUser = userSecurityService.getCurrentUser();
        return userSecurityService.isCurrentUser(Roles.ROLE_ADMIN) || currentUser.equals(user);
    }
}
