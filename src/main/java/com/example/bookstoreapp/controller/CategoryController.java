package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.request.category.CreateCategoryRequestDto;
import com.example.bookstoreapp.dto.response.book.BookDtoWithoutCategoryIds;
import com.example.bookstoreapp.dto.response.category.CategoryDto;
import com.example.bookstoreapp.service.category.CategoryService;
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

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new category (ADMIN)",
            description = "Create a new category. Provide category details in the request body.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Successfully created a new category")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public CategoryDto createCategory(CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(
            summary = "Get all categories (USER)",
            description = "Get a list of all available categories."
                    + " You can provide pagination information in the request body as JSON,"
                    + " e.g., {\"page\": 1, \"size\": 10, \"sort\": \"title,asc\"}."
    )
    @ApiResponse(responseCode = "200",
            description = "Successfully retrieved the list of categories")
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    @Operation(
            summary = "Find category by id (USER)",
            description = "Find a category by its id. Provide the category's ID in the URL path."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the category")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public CategoryDto findBookById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a category (ADMIN)",
            description = "Update a category by its id."
                    + " Provide the category's ID in the URL path"
                    + " and updated category details in the request body."
    )
    @ApiResponse(responseCode = "200", description = "Successfully updated the category")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public CategoryDto update(@PathVariable Long id,
                              @RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a category (ADMIN)",
            description = "Delete a category by its id. Provide the category's ID in the URL path."
    )
    @ApiResponse(responseCode = "204", description = "Successfully deleted the category")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/books")
    @Operation(
            summary = "Find all books by category id",
            description = "Find all books by category id. Provide the category's "
                    + "ID in the URL path."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id) {
        return categoryService.getBooksByCategoriesId(id);
    }
}
