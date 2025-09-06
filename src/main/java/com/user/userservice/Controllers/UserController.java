package com.user.userservice.Controllers;


import com.user.userservice.Models.User;
import com.user.userservice.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/login")

    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        boolean isPasswordValid = userService.verifyPassword(password, user.getPassword());
        if (!isPasswordValid) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        return ResponseEntity.ok("Login successful");
    }


}
