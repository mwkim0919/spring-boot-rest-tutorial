package com.mwkim.restwebservices.daos;

import com.mwkim.restwebservices.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Adam", LocalDateTime.now()));
        users.add(new User(2, "Eve", LocalDateTime.now()));
        users.add(new User(3, "Jack", LocalDateTime.now()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId()==null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
