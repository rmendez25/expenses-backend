package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.entities.Authority;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.models.UserModel;
import com.rmendez.expenses.backend.api.repositories.AuthorityRepository;
import com.rmendez.expenses.backend.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    PasswordEncoder encoder;

    public User saveUser(UserModel userModel) {
        User user = new User(
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getUsername(),
                encoder.encode(userModel.getPassword())
        );

        return userRepository.save(user);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser() {
        return null;
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
