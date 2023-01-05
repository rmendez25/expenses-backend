package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.models.UserModel;
import com.rmendez.expenses.backend.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('write')")
    public Iterable<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('read')")
    private User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserModel userModel) {
        return userService.updateUser();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted Successfully");
        userService.deleteUser(id);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PutMapping("/{userId}/authority/{authorityId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('write')")
    public User setAuthority(@PathVariable("userId") Long userId, @PathVariable("authorityId") Long authorityId) {
        return userService.setAuthority(userId, authorityId);
    }



}
