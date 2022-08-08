package com.fedormulashkin.usersandpetstask.service;

import com.fedormulashkin.usersandpetstask.dao.UserDAO;
import com.fedormulashkin.usersandpetstask.entity.Pet;
import com.fedormulashkin.usersandpetstask.entity.Role;
import com.fedormulashkin.usersandpetstask.entity.User;
import com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions.UserIsAlreadyExistsException;
import com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserIsAlreadyExistsException userIsAlreadyExistsException = new UserIsAlreadyExistsException("Registration failed. User with such name is already exists");
    private final UserNotFoundException userNotFoundException = new UserNotFoundException("User not found");

    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    @Transactional
    public User findUserById(int id) {
        return userDAO.findUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        String checkUser = null;
        try {
            checkUser = String.valueOf(userDAO.findByUsername(user.getUsername()));
        } catch (Exception e) {
            System.out.println("Пользователь не найден");
        }
        if (checkUser != null) {
            throw userIsAlreadyExistsException;
        }
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw userNotFoundException;
        }
        return user;
    }

    @Override
    @Transactional
    public String checkUsername(String username) {
        try {
            userDAO.findByUsername(username);
            return "This username isn't allowed";
        } catch (Exception e) {
            return "This username is allowed";
        }
    }

    @Override
    @Transactional
    public List<Pet> findAllUserPets(String name) {
        return userDAO.findAllUserPets(name);
    }

}
