package com.rolex.user.management.applicationv2.user_management_applicationv2;


import com.rolex.user.management.applicationv2.user_management_applicationv2.controller.UserController;
import com.rolex.user.management.applicationv2.user_management_applicationv2.dto.UserRequest;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.User;
import com.rolex.user.management.applicationv2.user_management_applicationv2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    public void setUp() {
        // Setting up example User and UserRequest data
        userRequest = new UserRequest();
        userRequest.setName("Arpit");
        userRequest.setEmail("arpit@epam.com");

        user = new User();
        user.setId(1);
        user.setName("Arpit");
        user.setEmail("arpit@epam.com");
    }

    @Test
    void shouldCreateUser() {
        // Given
        given(userService.create(any(UserRequest.class))).willReturn(user);

        // When
        userController.createUser(userRequest);

        // Then
        verify(userService).create(any(UserRequest.class));
    }

    @Test
    void shouldRetrieveAllUsers() {
        // Given
        Page<User> page = new PageImpl<>(List.of(user));
        given(userService.retrieveAll(any(Integer.class), any(Integer.class))).willReturn(page);

        // When
        Page<User> result = userController.retrieveAllUsers(0, 10);

        // Then
        verify(userService).retrieveAll(0, 10);
        assertNotNull("Users page should not be null", result);
        assertEquals("Expected users size in response", 1, result.getContent().size());
    }

    @Test
    void shouldGetUserById() {
        // Given
        given(userService.retrieveById(any(Integer.class))).willReturn(user);

        // When
        User foundUser = userController.getUserById(1);

        // Then
        verify(userService).retrieveById(1);
        assertNotNull("User should not be null", foundUser);
    }

    @Test
    void shouldUpdateUser() {
        // When
        userController.updateUser(userRequest);

        // Then
        verify(userService).update(any(UserRequest.class));
    }

    @Test
    void shouldDeleteUser() {
        // When
        userController.deleteUser(1);

        // Then
        verify(userService).delete(1);
    }
}

