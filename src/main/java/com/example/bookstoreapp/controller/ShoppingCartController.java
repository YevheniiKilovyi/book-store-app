package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.request.shoppingcart.AddCartItemRequestDto;
import com.example.bookstoreapp.dto.request.shoppingcart.UpdateBookQuantityInCartDto;
import com.example.bookstoreapp.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstoreapp.service.shoppingcart.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping carts")
@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Retrieve user's shopping cart (USER)",
            description = "Get a shopping cart of a current user.")
    public ShoppingCartResponseDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Add new book to shopping cart (USER)",
            description = "Add a new book to the shopping cart."
                    + " Provide bookId and quantity in the request body.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Successfully added a new book")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ShoppingCartResponseDto addBookToShoppingCart(
            @RequestBody @Valid AddCartItemRequestDto requestDto) {
        return shoppingCartService.addCartItem(requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update quantity of a book in the shopping cart (USER)",
            description = "Update quantity of a book in the shopping cart."
                    + " Provide cartItemId in URL path and quantity in the request body.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Successfully updated quantity of books")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ShoppingCartResponseDto updateQuantityOfBooksInShoppingCart(
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateBookQuantityInCartDto requestDto) {
        return shoppingCartService.updateQuantityOfBooks(cartItemId, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    @Operation(
            summary = "Delete a book from the shopping cart (USER)",
            description = "Delete a cart item from shopping cart by its id."
                    + " Provide the cart item's ID in the URL path."
    )
    @ApiResponse(responseCode = "204", description = "Successfully deleted the cart item")
    @ApiResponse(responseCode = "404", description = "Cart item not found")
    public void delete(@PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItem(cartItemId);
    }
}
