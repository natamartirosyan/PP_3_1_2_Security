package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    User findUser(int id);

    void editUserById (User user);

    void removeUserById(int id);

    List<User> getAllUsers();

}
