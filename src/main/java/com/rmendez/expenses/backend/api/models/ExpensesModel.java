package com.rmendez.expenses.backend.api.models;

import com.rmendez.expenses.backend.api.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpensesModel {
    private String name;
    private Category category;
    private Double amount;
    private LocalDateTime date = LocalDateTime.now();
}
