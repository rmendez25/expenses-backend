package com.rmendez.expenses.backend.api.repositories;

import com.rmendez.expenses.backend.api.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
}
