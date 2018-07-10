package com.michal.googleMapsViewer.repository;

import com.michal.googleMapsViewer.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    List<User> users = new ArrayList<>();

    public UsersRepository() {
        users.add(new User(1, "Marta", "Zajac", "marzaj", 25));
        users.add(new User(2, "Adam", "Nowak", "adanow", 30));
        users.add(new User(3, "Jacek", "Placek", "jacpla", 26));
        users.add(new User(4, "Jan", "Kowalski", "jankow", 19));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(final int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().get();
    }

    public User getUserByLogin(String login) {
        return users.stream().filter(u -> u.getLogin() == login).findFirst().get();
    }

    public List<User> getUsersList() {
        return users;
    }
}
