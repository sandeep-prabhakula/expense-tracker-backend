package com.sandeepprabhakula.ExpenseTracker.config;

import com.sandeepprabhakula.ExpenseTracker.data.User;
import com.sandeepprabhakula.ExpenseTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(UserInfoUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }
}
