package com.theabhikdatta.curewell.backend.service;

import com.theabhikdatta.curewell.backend.entity.Role;
import com.theabhikdatta.curewell.backend.payload.LoginDto;
import com.theabhikdatta.curewell.backend.payload.RegisterDto;

import java.util.List;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    String createRole(Role role);
    String deleteRole(String name);
    List<Role> getAllRoles();
    String getRoleByName(String name);
}
