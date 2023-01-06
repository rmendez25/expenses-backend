package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.Expenses;
import com.rmendez.expenses.backend.api.exception.ExpenseNotFoundException;
import com.rmendez.expenses.backend.api.exception.UserNotFoundException;
import com.rmendez.expenses.backend.api.models.ExpensesModel;
import com.rmendez.expenses.backend.api.services.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpensesController {

    private final ExpenseService expenseService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Expenses saveExpense(@RequestBody ExpensesModel expensesModel, @PathVariable Long id) {
        return expenseService.saveExpense(expensesModel, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Expenses> findAllExpenses() {
        return expenseService.findAllExpenses();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Expenses findExpenseById(@PathVariable Long id) throws ExpenseNotFoundException {
        return expenseService.findExpenseById(id);
    }

    @PutMapping("/expense/{id}/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Expenses updateExpense(@RequestBody Expenses expenses, @PathVariable("id") Long expenseId, @PathVariable("userId")  Long userId) throws ExpenseNotFoundException {
        return expenseService.updateExpense(expenses, expenseId, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteExpenseById(@PathVariable Long id) throws ExpenseNotFoundException {
        expenseService.deleteExpenseById(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
