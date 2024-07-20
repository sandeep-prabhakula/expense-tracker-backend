package com.sandeepprabhakula.ExpenseTracker.services.serviceImpl;

import com.sandeepprabhakula.ExpenseTracker.data.User;
import com.sandeepprabhakula.ExpenseTracker.dto.LoginDTO;
import com.sandeepprabhakula.ExpenseTracker.dto.LoginResponseDTO;
import com.sandeepprabhakula.ExpenseTracker.repositories.UserRepository;
import com.sandeepprabhakula.ExpenseTracker.services.JwtService;
import com.sandeepprabhakula.ExpenseTracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authMan;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> register(User user) {
        try {
            Optional<User> optionUser = userRepository.findByEmail(user.getEmail());
            Map<String, Object> response = new HashMap<>();
            if (optionUser.isPresent()) {
                response.put("status", 400);
                response.put("message", "User already exists with email " + user.getEmail());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                try {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
        Map<String, Object> responseBody = new HashMap<>();
        try{
            Authentication auth = authMan.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            Optional<User> response = userRepository.findByEmail(loginDTO.getEmail());
            if (auth.isAuthenticated() && response.isPresent()) {
                User user = response.get();
                return new ResponseEntity<>(new LoginResponseDTO(jwtService.generateToken(loginDTO.getEmail()), user.getId(),user.getEmail(), user.getName()), HttpStatus.OK);
            } else {

                responseBody.put("status", 400);
                responseBody.put("message", "Invalid Credentials");
                return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            responseBody.put("status",400);
            responseBody.put("message",e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateProfile(User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
            if (optionalUser.isEmpty()) {
                response.put("status", 400);
                response.put("message", "User with email " + user.getEmail() + " doesn't exists.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                User curUser = optionalUser.get();
                curUser.setName(user.getName());
                curUser.setSalaryPerMonth(user.getSalaryPerMonth());
                return new ResponseEntity<>(userRepository.save(curUser), HttpStatus.OK);
            }
        } catch (Exception e) {
            response.put("status",400);
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteProfile(User user) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
            Map<String, Object> response = new HashMap<>();
            if (optionalUser.isEmpty()) {
                response.put("status", 400);
                response.put("message", "User with email " + user.getEmail() + " doesn't exists.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                userRepository.delete(user);
                response.put("status", 200);
                response.put("message", "Profile deleted successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
