package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.request.book.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.book.BookDto;
import com.example.bookstoreapp.dto.searchParams.BookSearchParameters;
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
