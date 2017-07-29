package com.mwkim.restwebservices.controllers;

import com.mwkim.restwebservices.daos.UserDao;
import com.mwkim.restwebservices.exceptions.UserNotFoundException;
import com.mwkim.restwebservices.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    //retrieveAllUsers
    @GetMapping(path="/users")
    public List<User> retrieveAllUsers() {
        List<User> users = userDao.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("there is no users available.");
        }
        return users;
    }

    //retrieveUser(int id)
    @GetMapping(path="/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userDao.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }
        return user;
    }

    @PostMapping(path="/users")
    public ResponseEntity createUser(@RequestBody User user) {
        User savedUser = userDao.save(user);
        // created
        // /users/{id}  savedUser.getId()
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").
                buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
