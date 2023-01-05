package com.rmendez.expenses.backend.api.repositories;

import com.rmendez.expenses.backend.api.entities.Expenses;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.enums.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ExpensesRepositoryTest {
    @Autowired
    private ExpensesRepository expensesRepository;

    @Test
    public void saveExpense() {
        User user = new User("Robert", "Mendez", "mender", "password");

        Expenses expenses = Expenses.builder()
                .name("Claire School")
                .amount(7800.0)
                .category(Category.EDUCATION)
                .date(LocalDateTime.now())
                .user(user)
                .build();

        expensesRepository.save(expenses);

    }

}