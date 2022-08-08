package com.fedormulashkin.usersandpetstask.service;

import com.fedormulashkin.usersandpetstask.entity.Pet;
import com.fedormulashkin.usersandpetstask.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUsers();
    User findUserById(int id);
    void saveUser(User user);
    void deleteUserById(int id);
    String checkUsername(String username);
    List<Pet> findAllUserPets(String name);
}
