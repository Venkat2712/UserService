package com.user.userservice.Services;

import com.user.userservice.Models.User;
import com.user.userservice.userrepository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void registerUser(String plainPassword) {
        String hashedPassword = encoder.encode(plainPassword);
        // Save hashedPassword to the database
    }

    public boolean authenticate(String plainPassword, String storedHashedPassword) {
        return encoder.matches(plainPassword, storedHashedPassword);
    }


    public String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }

    public void createUser(User user) {
        // Logic to create user
       String hashedPassword =  hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);


    }

    public List<User> getUsers() {
        List<User> user = userRepository.findAll();
        return user;

    }

    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

