package com.rolex.user.management.applicationv2.user_management_applicationv2.repository;

import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
//    @Query("select u from User u")
//    List<User> getAllUser();
//    @Query("select u from User u where u.name=:n")
//    List<User> getUserByName(@Param("n")String name);
//
//    @Query(value = "select * from users", nativeQuery = true)
//    List<User> getUser();
//
//    @Query("select u from u where u.joiningDate>: date")
//    List<User> userafterJoining(@Param("date") Date date);


}
