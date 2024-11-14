package com.rolex.user.management.applicationv2.user_management_applicationv2;

import com.rolex.user.management.applicationv2.user_management_applicationv2.dto.UserRequest;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.Address;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.Role;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.User;
import com.rolex.user.management.applicationv2.user_management_applicationv2.exception.UserException;
import com.rolex.user.management.applicationv2.user_management_applicationv2.repository.RoleRepository;
import com.rolex.user.management.applicationv2.user_management_applicationv2.repository.UserRepository;
import com.rolex.user.management.applicationv2.user_management_applicationv2.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Test
	void contextLoads() {
	}

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void createTest() {
		UserRequest userRequest = new UserRequest(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		User user = new User(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		Role role = new Role(1, "DEV");

		when(roleRepository.findByRole("DEV")).thenReturn(role);
		when(userRepository.save(any(User.class))).thenReturn(user);

		User result = userService.create(userRequest);

		assertEquals(user.getEmail(), result.getEmail());
		verify(userRepository).save(any(User.class));
		verify(roleRepository).findByRole("DEV");


	}

	@Test
	void createThrowsExceptionTest() {
		UserRequest userRequest = new UserRequest(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		User user = new User(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		when(userRepository.save(any(User.class)))
				.thenThrow(new UserException("User cannot be updated with duplicate email", HttpStatus.CONFLICT));

		Exception exception = assertThrows(UserException.class, () -> userService.update(userRequest));

		assertEquals("User cannot be created with duplicate email", exception.getMessage());
		assertEquals(HttpStatus.CONFLICT, ((UserException) exception).getHttpStatus());

		verify(userRepository).save(any(User.class));
	}

	@Test
	void retrieveByIdTest(){
		User user = new User(1,"Arpit","arpit@epam.com",
				List.of(new Address(1,"Chennai")),List.of(new Role(1,"DEV")));
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		User result = userService.retrieveById(1);
		assertEquals(user,result);
		verify(userRepository).findById(1);
	}

	@Test
	void updateTest() {
		UserRequest userRequest = new UserRequest(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		User user = new User(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		Role role = new Role(1, "DEV");

		when(roleRepository.findByRole("DEV")).thenReturn(role);
		when(userRepository.save(any(User.class))).thenReturn(user);

		User result = userService.update(userRequest);

		assertEquals(user.getEmail(), result.getEmail());
		verify(userRepository).save(any(User.class));
		verify(roleRepository).findByRole("DEV");
	}

	@Test
	void updateThrowsExceptionTest() {
		UserRequest userRequest = new UserRequest(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		User user = new User(1, "Arpit", "arpit@epam.com",
				List.of(new Address(1, "Chennai")), List.of(new Role(1, "DEV")));

		when(userRepository.save(any(User.class)))
				.thenThrow(new UserException("User cannot be updated with duplicate email", HttpStatus.CONFLICT));

		Exception exception = assertThrows(UserException.class, () -> userService.update(userRequest));

		assertEquals("User cannot be updated with duplicate email", exception.getMessage());
		assertEquals(HttpStatus.CONFLICT, ((UserException) exception).getHttpStatus());

		verify(userRepository).save(any(User.class));
	}

	@Test//for pagination
	void retrieveAllTest(){

	}
	@Test
	void deleteTest() {

		Integer userId = 1;
		User user = new User();
		user.setId(userId);
		user.setRoles(new ArrayList<>(List.of(new Role(1, "DEV"))));
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		userService.delete(userId);
		verify(userRepository).findById(userId);
		verify(userRepository).save(user);
		verify(userRepository).deleteById(userId);
		assertTrue(user.getRoles().isEmpty());
	}
}
