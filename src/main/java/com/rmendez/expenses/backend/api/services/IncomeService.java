package com.rmendez.expenses.backend.api.services;

import com.rmendez.expenses.backend.api.entities.Income;
import com.rmendez.expenses.backend.api.entities.User;
import com.rmendez.expenses.backend.api.exception.IncomeNotFoundException;
import com.rmendez.expenses.backend.api.exception.UserNotFoundException;
import com.rmendez.expenses.backend.api.models.IncomeModel;
import com.rmendez.expenses.backend.api.repositories.IncomeRepository;
import com.rmendez.expenses.backend.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public Income saveIncome(IncomeModel incomeModel, Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }

        Income income = new Income();
        income.setIncomeName(incomeModel.getIncomeName());
        income.setIncomeAmount(incomeModel.getIncomeAmount());
        income.setUser(user.get());
        income.setCreatedAt(LocalDateTime.now());

        return incomeRepository.save(income);
    }

    public Iterable<Income> findAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income findIncomeById(Long id) {
        return incomeRepository.findById(id).get();
    }

    public Income updateIncome(Long incomeId, Long userId, Income income) throws UserNotFoundException, IncomeNotFoundException {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }

        Optional<Income> incomeDB = incomeRepository.findById(incomeId);

        if(!incomeDB.isPresent()) {
            throw new IncomeNotFoundException("Income not found");
        }

        Income incomeToUpdate = incomeDB.get();

        incomeToUpdate.setIncomeName(income.getIncomeName());
        incomeToUpdate.setIncomeAmount(income.getIncomeAmount());
        incomeToUpdate.setUpdatedAt(LocalDateTime.now());

        return incomeRepository.save(incomeToUpdate);

    }

    public void deleteIncome(Long id) throws IncomeNotFoundException {
        Optional<Income> income = incomeRepository.findById(id);

        if(!income.isPresent()) {
            throw new IncomeNotFoundException("Income not found");
        }

        incomeRepository.deleteById(id);

    }
}
