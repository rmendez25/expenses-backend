package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.entities.Authority;
import com.rmendez.expenses.backend.api.models.AuthorityModel;
import com.rmendez.expenses.backend.api.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority saveAuthority(AuthorityModel authorityModel) {
        Authority authority = new Authority();
        authority.setName(authorityModel.getName());
        return authorityRepository.save(authority);
    }

    public Iterable<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }
}
