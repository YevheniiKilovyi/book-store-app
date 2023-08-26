package com.example.bookstoreapp.mapper.user;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.user.UserLoginRequestDto;
import com.example.bookstoreapp.dto.response.user.UserLoginResponseDto;
import com.example.bookstoreapp.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public abstract class UserLoginMapper {
    public abstract UserLoginResponseDto toDto(User user);

    public abstract User toModel(UserLoginRequestDto requestDto);
}
