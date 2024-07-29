package com.redmath.Bank.App.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
        // Check if the user exists
        User existingUser = userService.findUser(userId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Check if the email is being updated and if it already exists for another user
        if (!existingUser.getEmail().equals(user.getEmail()) && userService.alreadyRegister(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // Update user details
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        // You can update other fields as needed
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User updatedUser = userService.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete User API
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        // Check if the user exists
        User existingUser = userService.findUser(userId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Delete the user
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
