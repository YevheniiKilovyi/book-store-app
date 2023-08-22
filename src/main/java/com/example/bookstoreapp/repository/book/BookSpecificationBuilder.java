package com.example.bookstoreapp.repository.book;

import com.example.bookstoreapp.dto.searchparams.BookSearchParameters;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.SpecificationBuilder;
import com.example.bookstoreapp.repository.SpecificationProviderManager;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.titles() != null && searchParameters.titles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(searchParameters.titles()));
        }
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParameters.authors()));
        }
        if (searchParameters.isbns() != null && searchParameters.isbns().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("isbn")
                    .getSpecification(
                            searchParameters.isbns()));
        }
        if (searchParameters.minPrices() != null && searchParameters.minPrices().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("minPrice")
                    .getSpecification(
                            searchParameters.minPrices()));
        }
        if (searchParameters.maxPrices() != null && searchParameters.maxPrices().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("maxPrice")
                    .getSpecification(
                            searchParameters.maxPrices()));
        }
        if (searchParameters.descriptions() != null && searchParameters.descriptions().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("description")
                    .getSpecification(
                            searchParameters.descriptions()));
        }
        return spec;
    }
}
