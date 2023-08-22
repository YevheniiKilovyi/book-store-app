package com.example.bookstoreapp.lib.emailPattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailPatternValidator implements ConstraintValidator<EmailValidator, String> {
    private static final String PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && email.matches(PATTERN);
    }
}