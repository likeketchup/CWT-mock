package com.example.customersystem.controller;

import com.example.customersystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/sendEmail")
    public String fetchToken(@RequestParam(value = "email") String email,
                          @RequestParam(value = "firstName") String firstName,
                          @RequestParam(value = "lastName") String lastName){
        customerService.fetchToken(email,firstName,lastName);
        return "got user data, sending email to CWT";
    }
}
