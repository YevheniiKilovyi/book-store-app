package com.example.bookstoreapp.dto.request.user;

import com.example.bookstoreapp.lib.passwordpattern.PasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @Size(min = 7, max = 50)
        @Email
        String email,
        @PasswordValidator
        String password
) {
}
