package com.example.bookstoreapp.repository.book.specification;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.SpecificationProvider;
import java.math.BigDecimal;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MinPriceSpecification implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "minPrice";
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            BigDecimal[] prices = Arrays.stream(params)
                    .map(p -> BigDecimal.valueOf(Long.parseLong(p)))
                    .toArray(BigDecimal[]::new);
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
                    Arrays.stream(prices).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO));
        };
    }
}
