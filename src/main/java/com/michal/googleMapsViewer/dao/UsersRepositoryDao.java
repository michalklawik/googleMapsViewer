package com.michal.googleMapsViewer.dao;

import com.michal.googleMapsViewer.domain.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersRepositoryDao {
    void addUser(User user);
    User getUserById(int id);
    User getUserByLogin(String login);
    List<User> getUsersList();
}
