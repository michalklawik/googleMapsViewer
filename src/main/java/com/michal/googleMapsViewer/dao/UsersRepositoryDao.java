package com.michal.googleMapsViewer.dao;

import com.michal.googleMapsViewer.domain.User;

import java.util.List;

public interface UsersRepositoryDao {
    void addUser(User user);
    User getUserById(int id);
    User getUserByLogin(String login);
    List<User> getUsersList();
}
