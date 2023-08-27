package com.example.bookstoreapp.service.order;

import com.example.bookstoreapp.dto.response.order.OrderItemResponseDto;
import com.example.bookstoreapp.model.OrderItem;

public interface OrderItemService {
    OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId, Long itemId);

    OrderItem save(OrderItem orderItem);
}
