package com.rmendez.expenses.backend.api.controllers;

import com.rmendez.expenses.backend.api.entities.Income;
import com.rmendez.expenses.backend.api.exception.IncomeNotFoundException;
import com.rmendez.expenses.backend.api.exception.UserNotFoundException;
import com.rmendez.expenses.backend.api.models.IncomeModel;
import com.rmendez.expenses.backend.api.services.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/incomes")
@AllArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Income saveIncome(@RequestBody IncomeModel incomeModel, @PathVariable Long id) throws UserNotFoundException {
        return incomeService.saveIncome(incomeModel, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Income> findAllIncomes() {
        return incomeService.findAllIncomes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Income findIncomeById(@PathVariable Long id) {
        return incomeService.findIncomeById(id);
    }

    @PutMapping("/income/{incomeId}/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Income updateIncome(@PathVariable Long incomeId, @PathVariable Long userId, @RequestBody Income income) throws UserNotFoundException, IncomeNotFoundException {
        return incomeService.updateIncome(incomeId, userId, income);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteIncome(@PathVariable Long id) throws IncomeNotFoundException {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted Successfully");
        incomeService.deleteIncome(id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
