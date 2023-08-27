package com.example.bookstoreapp.dto.request.shoppingcart;

import jakarta.validation.constraints.Positive;

public record UpdateBookQuantityInCartDto(@Positive int quantity) {
}
