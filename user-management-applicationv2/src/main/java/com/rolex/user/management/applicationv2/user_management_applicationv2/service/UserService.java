package com.rolex.user.management.applicationv2.user_management_applicationv2.service;


import com.rolex.user.management.applicationv2.user_management_applicationv2.dto.UserRequest;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User create(UserRequest userRequest);
    Page<User> retrieveAll(int pageNo, int pageSize);
    User retrieveById(Integer id);
    User update(UserRequest userRequest);
    void delete(Integer id);
}
