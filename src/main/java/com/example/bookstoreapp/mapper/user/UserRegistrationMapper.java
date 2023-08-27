package com.example.bookstoreapp.mapper.user;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.user.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.response.user.UserResponseDto;
import com.example.bookstoreapp.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public abstract class UserRegistrationMapper {
    public abstract UserResponseDto toDto(User user);

    public abstract User toModel(UserRegistrationRequestDto requestDto);
}
