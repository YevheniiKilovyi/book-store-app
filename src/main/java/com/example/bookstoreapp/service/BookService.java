package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;
import java.util.List;

public interface BookService {
    Book add(Book book);

    List<Book> getAll();
}
