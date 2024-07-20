package com.sandeepprabhakula.ExpenseTracker.services;

import com.sandeepprabhakula.ExpenseTracker.data.User;
import com.sandeepprabhakula.ExpenseTracker.dto.LoginDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?>register(User user);

    ResponseEntity<?>login(LoginDTO loginDTO);
    ResponseEntity<?>updateProfile(User user);
    ResponseEntity<?>deleteProfile(User user);
}
