package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.Expenses;
import com.rmendez.expenses.backend.api.models.ExpensesModel;
import com.rmendez.expenses.backend.api.services.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
