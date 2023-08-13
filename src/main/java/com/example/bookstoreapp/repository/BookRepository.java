package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import java.util.List;

public interface BookRepository {
    Book add(Book book);

    List<Book> getAll();
}
