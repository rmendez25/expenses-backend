package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.exception.UserNotFoundException;
import com.rmendez.expenses.backend.api.models.UserModel;
import com.rmendez.expenses.backend.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@Valid @RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private User findUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted Successfully");
        userService.deleteUser(id);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

}
