package com.agilemonkeys.crmapi.service.impl;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.agilemonkeys.crmapi.model.entity.User> optionalUser = userRepository.findByUsername(username);
        if ( optionalUser.isEmpty() ) {
            throw new UsernameNotFoundException(username);
        }
        com.agilemonkeys.crmapi.model.entity.User user = optionalUser.get();

        return new User(user.getUsername(), user.getPassword(), user.getRoles());
    }

}
