package com.example.bookstoreapp.mapper.book;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.book.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.book.BookDto;
import com.example.bookstoreapp.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
