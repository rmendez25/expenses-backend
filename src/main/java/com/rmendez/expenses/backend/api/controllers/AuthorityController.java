package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.Authority;
import com.rmendez.expenses.backend.api.models.AuthorityModel;
import com.rmendez.expenses.backend.api.services.AuthorityService;
import lombok.AllArgsConstructor;
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

}
