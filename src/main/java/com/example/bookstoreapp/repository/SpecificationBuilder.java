package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.dto.searchparams.BookSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParameters searchParameters);
}
