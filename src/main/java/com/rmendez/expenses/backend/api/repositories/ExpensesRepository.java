package com.rmendez.expenses.backend.api.repositories;

import com.rmendez.expenses.backend.api.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    @Query(value = "SELECT * FROM expenses WHERE user_id = :id", nativeQuery = true)
    Iterable<Expenses> findByUserId(@PathVariable Long id);

    @Query(value = "SELECT * FROM expenses WHERE category = :categoryName", nativeQuery = true)
    Iterable<Expenses> findByCategoryName(String categoryName);

    @Query(value = "SELECT * FROM expenses WHERE date BETWEEN :fromDate AND :toDate", nativeQuery = true)
    Iterable<Expenses> findByDates(LocalDate fromDate, LocalDate toDate);
}
