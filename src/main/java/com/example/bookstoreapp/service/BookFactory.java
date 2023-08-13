package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;

public interface BookFactory {
    Book generate(String title);
}
