package com.rmendez.expenses.backend.api.repositories;

import com.rmendez.expenses.backend.api.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Income findById(long id);
}
