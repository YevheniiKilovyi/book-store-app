package com.example.bookstoreapp.service.impl;

import java.util.List;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.BookService;
import org.springframework.stereotype.Component;
import com.example.bookstoreapp.repository.BookRepository;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book add(Book book) {
        return bookRepository.add(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }
}
