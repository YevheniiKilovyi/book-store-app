package com.example.bookstoreapp.repository.book.specification;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecification implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "isbn";
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get("isbn")
                .in(Arrays.stream(params).toArray());
    }
}
