package com.rmendez.expenses.backend.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeModel {
    private String incomeName;
    private Double incomeAmount;
}
