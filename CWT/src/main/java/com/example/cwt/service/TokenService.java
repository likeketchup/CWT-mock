package com.example.cwt.service;

import com.example.cwt.entity.Token;
import com.example.cwt.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class TokenService {
    @Autowired
    private KafkaTemplate<String, String> template;
    @Autowired
    private TokenRepository tokenRepository;
    @KafkaListener(topics = "fetchToken")
    public void fetchToken(String email){
        // email + first name + last name, and they arr seperated by space. Encode
        Optional<Token> optionalToken = tokenRepository.findById(email);
        if(optionalToken.isPresent()){
            System.out.println("token exists");
            Token currentToken = optionalToken.get();
            //check if timestamp is expired
            LocalTime current = LocalTime.now();
            LocalTime timestamp = LocalTime.parse(currentToken.getExpiration_timestamp());
            if(current.isBefore(timestamp.minusMinutes(2))){
                //request success, print the token
                System.out.println(currentToken.getToken());
            }else{
                //expired, updating token
                System.out.println("token expired, updating token");
                current = current.plusMinutes(5);
                String encodedToken = email+" "+current.toString();
                Token token = new Token(email, encode(encodedToken), current.toString());
                tokenRepository.save(token);
                System.out.println(token.getToken());
            }
        }else{
            System.out.println("token not found, creating a new one");
            LocalTime time = LocalTime.now();
            time = time.plusMinutes(5);
            String encodedToken = email+" "+time.toString();
            Token token = new Token(email, encode(encodedToken), time.toString());
            tokenRepository.save(token);
        }
    }
    private String encode(String input){
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
