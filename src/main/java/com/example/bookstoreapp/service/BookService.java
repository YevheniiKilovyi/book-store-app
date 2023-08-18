package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookSearchParameters;
import com.example.bookstoreapp.dto.request.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.BookDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParameters params);
}
