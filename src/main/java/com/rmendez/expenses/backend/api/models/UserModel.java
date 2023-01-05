package com.rmendez.expenses.backend.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String firstName;

    private String lastName;

    private String username;

    private String password;
}
