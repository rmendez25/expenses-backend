package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.entities.Authority;
import com.rmendez.expenses.backend.api.entities.Expenses;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.exception.UserNotFoundException;
import com.rmendez.expenses.backend.api.models.UserModel;
import com.rmendez.expenses.backend.api.repositories.AuthorityRepository;
import com.rmendez.expenses.backend.api.repositories.ExpensesRepository;
import com.rmendez.expenses.backend.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private ExpensesRepository expensesRepository;

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

    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> user =  userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        return user.get();
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> user =  userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.deleteById(id);

    }

    public User updateUser(Long id, User user) throws UserNotFoundException {
        Optional<User> userDB = userRepository.findById(id);

        if (userDB.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User userToUpdate = userDB.get();

        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
            userToUpdate.setFirstName(user.getFirstName());
        }

        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            userToUpdate.setLastName(user.getLastName());
        }

        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            userToUpdate.setPassword(encoder.encode(user.getPassword()));
        }

        userToUpdate.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(userToUpdate);
    }

}
