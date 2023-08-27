package com.example.bookstoreapp.dto.request.order;

import com.example.bookstoreapp.model.Status;
import jakarta.validation.constraints.NotBlank;

public record UpdateOrderStatusRequestDto(@NotBlank Status status) {
}
