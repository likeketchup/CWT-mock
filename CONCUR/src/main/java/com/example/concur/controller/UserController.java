package com.example.concur.controller;

import com.example.concur.entity.User;
import com.example.concur.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(value = "/saveJson")
    public String saveJson(@RequestBody List<User> users){
        try {
            service.save(users);
            System.out.println("Users Saved!");
        } catch (Exception e){
            System.out.println("Unable to save users: " + e.getMessage());
        }
        return "save json to db successed";
    }
    @GetMapping("/list")
    public Iterable<User> list() {
        return service.list();
    }
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam(value = "id") Integer id) {
        service.delete(id);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user){
        service.update(user);
    }
}
