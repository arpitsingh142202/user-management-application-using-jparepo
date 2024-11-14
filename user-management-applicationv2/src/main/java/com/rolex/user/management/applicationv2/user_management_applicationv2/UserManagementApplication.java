package com.rolex.user.management.applicationv2.user_management_applicationv2;

import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.User;
import com.rolex.user.management.applicationv2.user_management_applicationv2.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(UserManagementApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
//
//		List<User> allUser = userRepository.getAllUser();
//		allUser.forEach(System.out::println);
//		System.out.println("------------------------ ");
//
//		List<User> userByName = userRepository.getUserByName("Virat");
//		userByName.forEach(System.out::println);
//
//		System.out.println("------------------------------");
//		userRepository.getUser().forEach(System.out::println);

	}

}
