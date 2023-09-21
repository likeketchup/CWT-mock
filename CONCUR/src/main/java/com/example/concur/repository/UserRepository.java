package com.example.concur.repository;

import com.example.concur.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM User WHERE email=?",nativeQuery = true)
    public User findByEmail(String email);
}
