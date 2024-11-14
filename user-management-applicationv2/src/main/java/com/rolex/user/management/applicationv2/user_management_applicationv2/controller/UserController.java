package com.rolex.user.management.applicationv2.user_management_applicationv2.controller;

import com.rolex.user.management.applicationv2.user_management_applicationv2.dto.UserRequest;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.User;
import com.rolex.user.management.applicationv2.user_management_applicationv2.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public void createUser(@Valid @RequestBody UserRequest userRequest){
        log.info("Received request to create user with Name: {}", userRequest.getName());
        userService.create(userRequest);
    }

    @GetMapping("{pageNo}/{pageSize}")
    public Page<User> retrieveAllUsers(@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        return userService.retrieveAll(pageNo,pageSize);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Integer id){
        log.info("Received request to fetch user with ID: {}", id);
        return userService.retrieveById(id);
    }
    

    @PutMapping
    public void updateUser(@Valid @RequestBody UserRequest userRequest){
        log.info("Received request to update user with ID: {}", userRequest.getId());
        userService.update(userRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id){
        log.info("Received request to delete user with ID: {}", id);
        userService.delete(id);
    }
}
