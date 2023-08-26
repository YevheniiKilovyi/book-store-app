package com.example.bookstoreapp.repository.book.specification;

import com.example.bookstoreapp.dto.searchparams.BookSearchParameters;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.SpecificationBuilder;
import com.example.bookstoreapp.repository.SpecificationProvider;
import com.example.bookstoreapp.repository.SpecificationProviderManager;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final Map<String, String> PARAMETER_TO_PROVIDER_MAP = Map.of(
            "title", "title",
            "author", "author",
            "isbn", "isbn",
            "minPrice", "minPrice",
            "maxPrice", "maxPrice",
            "description", "description"
    );
    private SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);

        for (Map.Entry<String, String> entry : PARAMETER_TO_PROVIDER_MAP.entrySet()) {
            String paramName = entry.getKey();
            String providerName = entry.getValue();

            String[] values = getValueFromSearchParameters(searchParameters, paramName);
            if (values != null && values.length > 0) {
                SpecificationProvider<Book> provider = bookSpecificationProviderManager
                        .getSpecificationProvider(providerName);
                Specification<Book> providerSpec = provider.getSpecification(values);
                spec = spec.and(providerSpec);
            }
        }

        return spec;
    }

    private String[] getValueFromSearchParameters(
            BookSearchParameters searchParameters, String paramName) {
        switch (paramName) {
            case "title":
                return searchParameters.titles();
            case "author":
                return searchParameters.authors();
            case "isbn":
                return searchParameters.isbns();
            case "minPrice":
                return searchParameters.minPrices();
            case "maxPrice":
                return searchParameters.maxPrices();
            case "description":
                return searchParameters.descriptions();
            default:
                return null;
        }
    }
}
