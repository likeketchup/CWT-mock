package com.example.concur.controller;

import com.example.concur.entity.User;
import com.example.concur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Base64;


@RestController
public class TokenController {
    @Autowired
    private UserService service;
    @PostMapping(value = "/getUserInfo")
    public void sendMessage(@RequestParam(value = "token") String token){
        //String[] infos = decode(token);
        service.send(token);
        //return "token is valid and we are going to fetch data";
    }
    //id + email + expired time

}
