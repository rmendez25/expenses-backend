package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.entities.Expenses;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.enums.Category;
import com.rmendez.expenses.backend.api.models.ExpensesModel;
import com.rmendez.expenses.backend.api.repositories.ExpensesRepository;
import com.rmendez.expenses.backend.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpensesRepository expensesRepository;
    private final UserRepository userRepository;

    public Expenses saveExpense(ExpensesModel expensesModel, Long id) {
        User user = userRepository.findById(id).get();
        Expenses expenses = new Expenses();
        expenses.setName(expensesModel.getName());
        expenses.setUser(user);
        expenses.setAmount(expensesModel.getAmount());
        expenses.setCategory(expensesModel.getCategory());
        expenses.setDate(expensesModel.getDate());

        return expensesRepository.save(expenses);
    }
}
