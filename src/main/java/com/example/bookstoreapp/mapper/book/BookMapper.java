package com.example.bookstoreapp.mapper.book;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.book.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.book.BookDto;
import com.example.bookstoreapp.dto.response.book.BookDtoWithoutCategoryIds;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public abstract class BookMapper {
    public abstract BookDto toDto(Book book);

    public abstract Book toModel(CreateBookRequestDto requestDto);

    public abstract BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    public void setCategoryIdsToDto(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIds(
                book.getCategories()
                        .stream()
                        .map(Category::getId)
                        .toList());
    }
}
