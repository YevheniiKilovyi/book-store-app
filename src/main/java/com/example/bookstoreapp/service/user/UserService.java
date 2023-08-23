package com.example.bookstoreapp.service.user;

import com.example.bookstoreapp.dto.request.user.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.response.user.UserResponseDto;
import com.example.bookstoreapp.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
