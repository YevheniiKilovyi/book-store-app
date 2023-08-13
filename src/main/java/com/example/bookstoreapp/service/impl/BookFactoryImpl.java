package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.BookFactory;
import java.util.Random;

public class BookFactoryImpl implements BookFactory {
    private static final String[] adjectives = new String[]{"interesting", "exciting", "scient", "detective", "biographical"};
    private static final String[] authors = new String[]{"Bob Johnson", "Alice Hangover", "Taras Shevchenko"};

    @Override
    public Book create() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("It's a ");
        for (int i = 0; i < 2; i++) {
            String randomAdjectives = adjectives[random.nextInt(adjectives.length)];
            sb.append(randomAdjectives).append(" ");
        }
        String description = sb.toString();
        Book book = new Book();
        book.setAuthor(authors[random.nextInt(authors.length)]);
        book.setDescription(description);
        return book;
    }
}
