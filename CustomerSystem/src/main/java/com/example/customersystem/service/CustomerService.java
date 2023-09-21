package com.example.customersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private KafkaTemplate<String, String> template;
    @KafkaListener(topics = "userInfo")
    public void getMessageEmail(String userInfo){
        System.out.println(userInfo);
    }

    public void fetchToken(String email, String firstname, String lastName){
        template.send( "fetchToken", email);
    }
}
