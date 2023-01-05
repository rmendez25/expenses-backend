package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.repositories.ExpensesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpensesRepository expensesRepository;
}
