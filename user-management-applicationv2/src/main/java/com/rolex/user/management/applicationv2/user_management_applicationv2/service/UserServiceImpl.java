package com.rolex.user.management.applicationv2.user_management_applicationv2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rolex.user.management.applicationv2.user_management_applicationv2.dto.UserRequest;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.Role;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.User;
import com.rolex.user.management.applicationv2.user_management_applicationv2.exception.UserException;
import com.rolex.user.management.applicationv2.user_management_applicationv2.repository.RoleRepository;
import com.rolex.user.management.applicationv2.user_management_applicationv2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

//    @Autowired
//    ObjectMapper objectMapper;

    @Override
    public User create(UserRequest userRequest) {
//        User user = objectMapper.convertValue(userRequest,User.class);
        log.info("Creating a new user with email: {}", userRequest.getEmail());
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAddresses(userRequest.getAddresses());

        List<Role> assignedRoles = new ArrayList<>();
        for(Role roleRequest : userRequest.getRoles()){
            Role role = roleRepository.findByRole(roleRequest.getRole());
            if(role == null){
                role = new Role();
                role.setRole(roleRequest.getRole());
                roleRepository.save(role);
                log.debug("New role created: {}", role.getRole());
            }
            assignedRoles.add(role);
        }

        user.setRoles(assignedRoles);
        try{
            userRepository.save(user);
            log.info("User created successfully with ID: {}", user.getId());
        } catch (Exception ex){
            log.error("Error occurred while creating a User");
            throw new UserException("User cannot be created with duplicate email",
                    HttpStatus.CONFLICT);
        }
        return user;
    }

    @Override
    public Page<User> retrieveAll(int pageNo, int pageSize)  {
        return userRepository.findAll(PageRequest.of(pageNo,pageSize));
    }


    @Override
    public User retrieveById(Integer id) {

        return userRepository.findById(id)
                .orElseThrow(()-> new UserException("User not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public User update(UserRequest userRequest) {
//        User user = objectMapper.convertValue(userRequest,User.class);

        User user = new User();
        user.setId(userRequest.getId());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAddresses(userRequest.getAddresses());

        List<Role> assignedRoles = new ArrayList<>();
        for(Role roleRequest : userRequest.getRoles()){
            Role role = roleRepository.findByRole(roleRequest.getRole());
            if(role == null){
                role = new Role();
                role.setRole(roleRequest.getRole());
                roleRepository.save(role);
            }
            assignedRoles.add(role);
        }

        user.setRoles(assignedRoles);
        try{
            userRepository.save(user);
        } catch (Exception ex){
            log.error("Error occurred while updating User");
            throw new UserException("User cannot be updated with duplicate email",
                    HttpStatus.CONFLICT);
        }

        return user;
    }


    @Override
    public void delete(Integer id) {
        log.info("Deleting user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserException("User not found with id: " + id,HttpStatus.NOT_FOUND));
        user.getRoles().clear();
        userRepository.save(user);
        userRepository.deleteById(id);
        log.info("User deleted successfully");
    }
}





