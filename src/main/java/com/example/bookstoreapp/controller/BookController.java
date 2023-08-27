package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.request.book.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.book.BookDto;
import com.example.bookstoreapp.dto.response.book.BookDtoWithoutCategoryIds;
import com.example.bookstoreapp.dto.searchparams.BookSearchParameters;
import com.example.bookstoreapp.service.book.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(
            summary = "Get all books (USER)",
            description = "Get a list of all available books."
                    + " You can provide pagination information in the request body as JSON,"
                    + " e.g., {\"page\": 1, \"size\": 10, \"sort\": \"title,asc\"}."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books")
    public List<BookDtoWithoutCategoryIds> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    @Operation(
            summary = "Find book by id (USER)",
            description = "Find a book by its id. Provide the book's ID in the URL path."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the book")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public BookDto findBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new book (ADMIN)",
            description = "Create a new book. Provide book details in the request body.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Successfully created a new book")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a book (ADMIN)",
            description = "Update a book by its id."
                    + " Provide the book's ID in the URL path"
                    + " and updated book details in the request body."
    )
    @ApiResponse(responseCode = "200", description = "Successfully updated the book")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public BookDto update(@PathVariable Long id,
                          @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a book (ADMIN)",
            description = "Delete a book by its id. Provide the book's ID in the URL path."
    )
    @ApiResponse(responseCode = "204", description = "Successfully deleted the book")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/search")
    @Operation(
            summary = "Find book by params (USER)",
            description = "Find a book by input specifications."
                    + " Provide search parameters in the query string."
    )
    @ApiResponse(responseCode = "200",
            description = "Successfully retrieved the list of books based on search")
    public List<BookDto> search(BookSearchParameters searchParameters) {
        return bookService.search(searchParameters);
    }
}
