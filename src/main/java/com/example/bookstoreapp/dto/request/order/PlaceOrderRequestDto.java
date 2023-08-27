package com.example.bookstoreapp.dto.request.order;

import jakarta.validation.constraints.NotBlank;

public record PlaceOrderRequestDto(@NotBlank String shippingAddress) {
}
