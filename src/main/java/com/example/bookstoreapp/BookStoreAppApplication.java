package com.example.bookstoreapp;

import java.math.BigDecimal;
import com.example.bookstoreapp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.bookstoreapp.service.BookService;

@SpringBootApplication
public class BookStoreAppApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Atlas Shrugged");
            book.setAuthor("Ayn Rand");
            book.setIsbn("0-9767736-6-X or 978-0-9767736-6-5");
            book.setPrice(BigDecimal.valueOf(50));
            book.setDescription("Nice book");
            book.setCoverImage("1");
            bookService.add(book);

            System.out.println(bookService.getAll());
        };
    }

}
