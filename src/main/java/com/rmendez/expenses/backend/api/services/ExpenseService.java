package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.entities.Expenses;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.exception.ExpenseNotFoundException;
import com.rmendez.expenses.backend.api.exception.UserNotFoundException;
import com.rmendez.expenses.backend.api.models.ExpensesModel;
import com.rmendez.expenses.backend.api.repositories.ExpensesRepository;
import com.rmendez.expenses.backend.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpensesRepository expensesRepository;
    private final UserRepository userRepository;

    public Expenses saveExpense(ExpensesModel expensesModel, Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        Expenses expenses = new Expenses();
        expenses.setName(expensesModel.getName());
        expenses.setUser(user.get());
        expenses.setAmount(expensesModel.getAmount());
        expenses.setCategory(expensesModel.getCategory());
        expenses.setDate(expensesModel.getDate());

        return expensesRepository.save(expenses);
    }

    public List<Expenses> findAllExpenses() {
        return expensesRepository.findAll();
    }

    public Expenses findExpenseById(Long id) throws ExpenseNotFoundException {
        Optional<Expenses> expense = expensesRepository.findById(id);

        if(expense.isEmpty()) {
            throw new ExpenseNotFoundException("Expense Not Found");
        }

        return expense.get();
    }

    public Expenses updateExpense(Expenses expenses, Long id, Long userId) throws ExpenseNotFoundException, UserNotFoundException {
        Optional<Expenses> expenseDB = expensesRepository.findById(id);

        if (expenseDB.isEmpty()) {
            throw new ExpenseNotFoundException("Expense Not Found");
        }

        Expenses expenseToUpdate = expenseDB.get();

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        expenses.setUser(user.get());

        if (Objects.nonNull(expenses.getName()) && !"".equalsIgnoreCase(expenses.getName())) {
            expenseToUpdate.setName(expenses.getName());
        }

        if (Objects.nonNull(expenses.getAmount())) {
            expenseToUpdate.setAmount(expenses.getAmount());
        }

        if (Objects.nonNull(expenses.getCategory())) {
            expenseToUpdate.setCategory(expenses.getCategory());
        }
        if (Objects.nonNull(expenses.getUser())) {
            expenseToUpdate.setUser(user.get());
        }

       return expensesRepository.save(expenseToUpdate);
    }

    public void deleteExpenseById(Long id) throws ExpenseNotFoundException {
        Optional<Expenses> expense = expensesRepository.findById(id);

        if(expense.isEmpty()) {
            throw new ExpenseNotFoundException("Expense Not Found");
        }

        expensesRepository.deleteById(id);
    }
}
