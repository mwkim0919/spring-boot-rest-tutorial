package com.mwkim.restwebservices.controllers;

import com.mwkim.restwebservices.daos.UserDao;
import com.mwkim.restwebservices.exceptions.UserNotFoundException;
import com.mwkim.restwebservices.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
        return users;
    }

    //retrieveUser(int id)
    @GetMapping(path="/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = userDao.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }
        //HATEOAS
        //all-users, SERVER_PATH + "/users"
        //retrieveAllUsers
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping(path="/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
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

    @DeleteMapping(path="/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User user = userDao.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }
        return ResponseEntity.noContent().build();
    }

}
