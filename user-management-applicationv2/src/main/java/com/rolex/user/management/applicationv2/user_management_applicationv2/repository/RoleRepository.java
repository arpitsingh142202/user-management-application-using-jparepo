package com.rolex.user.management.applicationv2.user_management_applicationv2.repository;

import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRole(String role);

}
