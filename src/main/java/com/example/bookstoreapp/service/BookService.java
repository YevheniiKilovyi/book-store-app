package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookSearchParameters;
import com.example.bookstoreapp.dto.request.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.BookDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParameters params);
}
