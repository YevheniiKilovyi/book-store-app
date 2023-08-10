package com.example.bookstoreapp.repository.impl;

import com.example.bookstoreapp.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.example.bookstoreapp.repository.AbstractRepository;
import com.example.bookstoreapp.repository.BookRepository;

@Repository
public class BookRepositoryImpl extends AbstractRepository<Book> implements BookRepository {
    public BookRepositoryImpl(SessionFactory factory) {
        super(factory, Book.class);
    }
}
