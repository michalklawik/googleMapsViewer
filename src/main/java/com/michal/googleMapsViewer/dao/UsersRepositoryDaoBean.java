package com.michal.googleMapsViewer.dao;

import com.michal.googleMapsViewer.domain.User;
import com.michal.googleMapsViewer.repository.UsersRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UsersRepositoryDaoBean implements UsersRepositoryDao {

    @EJB
    UsersRepository usersRepository = new UsersRepository();

    @Override
    public void addUser(User user) {
        usersRepository.addUser(user);
    }

    @Override
    public User getUserById(int id) {
        return usersRepository.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return usersRepository.getUserByLogin(login);
    }

    @Override
    public List<User> getUsersList() {
        return usersRepository.getUsersList();
    }
}
