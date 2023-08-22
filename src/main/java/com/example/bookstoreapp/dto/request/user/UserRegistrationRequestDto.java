package com.example.bookstoreapp.dto.request.user;

import com.example.bookstoreapp.lib.fieldMatch.FieldMatch;
import com.example.bookstoreapp.lib.passwordPattern.PasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords don't match!"
)
public class UserRegistrationRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 30)
    @PasswordValidator
    private String password;
    private String repeatPassword;
    @NotBlank
    @Size(min = 1, max = 40)
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 40)
    private String lastName;
    @NotBlank
    @Size(max = 100)
    private String shippingAddress;
}
