package com.michal.googleMapsViewer.repository;

import com.michal.googleMapsViewer.domain.Gender;
import com.michal.googleMapsViewer.domain.User;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersRepository {
    List<User> users = new ArrayList<>();

    public UsersRepository() {
        users.add(new User(1, "Marta", "Zajac", "marzaj", 25, Gender.WOMAN));
        users.add(new User(2, "Adam", "Nowak", "adanow", 30, Gender.MAN));
        users.add(new User(3, "Jacek", "Placek", "jacpla", 26, Gender.MAN));
        users.add(new User(4, "Jan", "Kowalski", "jankow", 19, Gender.MAN));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(final int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().get();
    }

    public User getUserByLogin(String login) {
        return users.stream().filter(u -> u.getLogin().equals(login)).findFirst().get();
    }

    public List<User> getUsersList() {
        return users;
    }
}
