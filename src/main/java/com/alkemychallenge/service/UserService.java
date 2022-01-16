package com.alkemychallenge.service;

import com.alkemychallenge.model.entity.Role;
import com.alkemychallenge.model.entity.User;

import java.util.List;

public interface UserService {

    void registerUser(User user);

    void saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
