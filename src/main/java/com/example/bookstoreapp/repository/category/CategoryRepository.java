package com.example.bookstoreapp.repository.category;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :id")
    List<Book> getBooksByCategoriesId(Long id);

    @EntityGraph(attributePaths = "books")
    Optional<Category> findById(Long id);
}
