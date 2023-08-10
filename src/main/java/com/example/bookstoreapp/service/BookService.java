package com.example.bookstoreapp.service;

import java.util.List;
import com.example.bookstoreapp.model.Book;

public interface BookService {
    Book add(Book book);

    List<Book> getAll();
}
