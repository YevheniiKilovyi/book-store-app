package com.example.bookstoreapp.repository.book.specification;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.SpecificationProvider;
import com.example.bookstoreapp.repository.SpecificationProviderManager;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key)).findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification provider or key " + key));
    }
}
