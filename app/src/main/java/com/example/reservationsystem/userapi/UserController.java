package com.example.reservationsystem.userapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/{nationalId}")
    public ResponseEntity<User> getUserByNationalId(@PathVariable String nationalId) {
        User user = userService.getUser(nationalId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
