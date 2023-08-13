package com.example.bookstoreapp.repository.impl;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.AbstractRepository;
import com.example.bookstoreapp.repository.BookRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends AbstractRepository<Book> implements BookRepository {
    public BookRepositoryImpl(SessionFactory factory) {
        super(factory, Book.class);
    }
}
