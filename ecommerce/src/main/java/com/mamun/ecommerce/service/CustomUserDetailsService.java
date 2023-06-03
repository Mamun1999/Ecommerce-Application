package com.mamun.ecommerce.service;

import com.mamun.ecommerce.dao.UserRepository;
import com.mamun.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
@Autowired
private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user= this.userRepository.findById(username);
        if(user==null){
            throw  new UsernameNotFoundException("User not found");
        }

        return user.get();
    }
}
