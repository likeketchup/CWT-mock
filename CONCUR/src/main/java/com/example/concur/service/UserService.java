package com.example.concur.service;

import com.example.concur.entity.User;
import com.example.concur.repository.UserRepository;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    @Autowired
    private KafkaTemplate<String, String> template;
    @Autowired
    private UserRepository userRepository;
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void save(List<User> users) {
        userRepository.saveAll(users);
    }
    public void send(String token){
        //decoding
        String[] decodedToken = decode(token);
        String email = decodedToken[0];
        String expirationTime = decodedToken[1];
        //parse time
        LocalTime current = LocalTime.now();
        LocalTime timestamp = LocalTime.parse(expirationTime);
        LOGGER.info("current time:"+current.toString());
        LOGGER.info("time stamp: " + timestamp.toString());
        //check if the timestamp in this token is expired or not
        if(current.isBefore(timestamp.minusMinutes(2))){
            //reject
            System.out.println("success");
            User user = userRepository.findByEmail(email);
            template.send( "userInfo", user.toString());
            return;
        }else{
            System.out.println("time expired, requesting a new one");
            //request a new one
            template.send( "fetchToken", email);
        }
    }
    private String[] decode(String token){
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedString = new String(decodedBytes);
        return decodedString.split(" ");
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(User user) {
        userRepository.save(user);


    }
}
