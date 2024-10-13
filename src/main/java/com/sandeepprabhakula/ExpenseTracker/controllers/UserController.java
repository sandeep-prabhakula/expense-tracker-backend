package com.sandeepprabhakula.ExpenseTracker.controllers;

import com.sandeepprabhakula.ExpenseTracker.data.User;
import com.sandeepprabhakula.ExpenseTracker.dto.LoginDTO;
import com.sandeepprabhakula.ExpenseTracker.services.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    @GetMapping("/job")
    public String hello(){
        return "hello world";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try{
            return userService.login(loginDTO);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        try{
            return userService.register(user);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> update(@RequestBody User user){
        try{
            return userService.updateProfile(user);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-profile")
    public ResponseEntity<?> delete(@RequestBody User user){
        try{
            return userService.deleteProfile(user);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-user-details/{id}")
    public ResponseEntity<?>getUserDetails(@PathVariable("id") String id){
        try{
            return userService.getUserDetails(id);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
