package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.Authority;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.models.AuthorityModel;
import com.rmendez.expenses.backend.api.services.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorities")
@AllArgsConstructor
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping
    public Authority saveAuthority(@RequestBody AuthorityModel authorityModel) {
        return authorityService.saveAuthority(authorityModel);
    }

    @GetMapping
    public Iterable<Authority> findAllAuthorities() {
        return authorityService.findAllAuthorities();
    }


    @PutMapping("/authority/{authorityId}/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User setAuthority(@PathVariable("authorityId") Long authorityId, @PathVariable("userId") Long userId) {
        return authorityService.setAuthority(userId, authorityId);
    }

}
