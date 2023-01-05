package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.Authority;
import com.rmendez.expenses.backend.api.models.AuthorityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @PostMapping
    public Authority saveAuthority(@RequestBody AuthorityModel authorityModel) {
        return authorityService.saveAuthority(authorityModel);
    }

    @GetMapping
    public Iterable<Authority> findAllAuthorities() {
        return authorityService.findAllAuthorities();
    }

}
