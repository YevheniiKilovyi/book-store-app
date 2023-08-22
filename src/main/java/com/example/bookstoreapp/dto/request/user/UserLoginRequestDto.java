package com.example.bookstoreapp.dto.request.user;

import com.example.bookstoreapp.lib.passwordPattern.PasswordValidator;
import jakarta.validation.constraints.Email;

public class UserLoginRequestDto {
    @Email
    private String email;
    @PasswordValidator
    private String passwword;
}
