package com.example.bookstoreapp.dto.response.order;

import com.example.bookstoreapp.model.Status;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemResponseDto> orderItems;
    private LocalDate orderDate;
    private BigDecimal total;
    private Status status;
}
