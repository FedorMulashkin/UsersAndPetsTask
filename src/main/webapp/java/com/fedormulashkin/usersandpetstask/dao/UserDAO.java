package com.fedormulashkin.usersandpetstask.dao;

import com.fedormulashkin.usersandpetstask.entity.Pet;
import com.fedormulashkin.usersandpetstask.entity.User;

import java.util.List;


public interface UserDAO {
    List<User> findAllUsers();
    User findUserById(int id);
    User findByUsername(String username);
    void saveUser(User user);
    void deleteUser(User user);
    void deleteUserById(int id);

    List<Pet> findAllUserPets(String name);
}
