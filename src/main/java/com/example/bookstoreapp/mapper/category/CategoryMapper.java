package com.example.bookstoreapp.mapper.category;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.category.CreateCategoryRequestDto;
import com.example.bookstoreapp.dto.response.category.CategoryDto;
import com.example.bookstoreapp.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public abstract class CategoryMapper {
    public abstract CategoryDto toDto(Category category);

    public abstract Category toModel(CreateCategoryRequestDto requestDto);
}
