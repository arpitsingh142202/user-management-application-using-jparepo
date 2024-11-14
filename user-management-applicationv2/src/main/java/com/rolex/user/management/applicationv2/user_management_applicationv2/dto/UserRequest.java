package com.rolex.user.management.applicationv2.user_management_applicationv2.dto;

import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.Address;
import com.rolex.user.management.applicationv2.user_management_applicationv2.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email
    @NotEmpty
    private String email;

    @NotNull
    private List<Address> addresses;

    @NotNull
    private List<Role> roles;

}
