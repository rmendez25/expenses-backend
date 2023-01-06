package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.entities.Authority;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.models.AuthorityModel;
import com.rmendez.expenses.backend.api.repositories.AuthorityRepository;
import com.rmendez.expenses.backend.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorityService {


    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    public Authority saveAuthority(AuthorityModel authorityModel) {
        Authority authority = new Authority();
        authority.setName(authorityModel.getName());
        return authorityRepository.save(authority);
    }

    public Iterable<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    public User setAuthority(Long userId, Long authorityId) {
        User user = userRepository.findById(userId).get();
        user.setUpdatedAt(LocalDateTime.now());
        Authority authority = authorityRepository.findById(authorityId).get();

        List<Authority> authorities = null;
        authorities = user.getAuthorities();
        authorities.add(authority);

        user.setAuthorities(authorities);
        return userRepository.save(user);
    }
}
