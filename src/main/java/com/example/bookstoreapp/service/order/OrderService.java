package com.example.bookstoreapp.service.order;

import com.example.bookstoreapp.dto.request.order.PlaceOrderRequestDto;
import com.example.bookstoreapp.dto.request.order.UpdateOrderStatusRequestDto;
import com.example.bookstoreapp.dto.response.order.OrderItemResponseDto;
import com.example.bookstoreapp.dto.response.order.OrderResponseDto;
import com.example.bookstoreapp.model.ShoppingCart;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto placeOrder(PlaceOrderRequestDto requestDto);

    List<OrderResponseDto> getOrderHistory(Pageable pageable);

    List<OrderItemResponseDto> getOrderItems(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long itemId);

    void updateOrderStatus(Long orderId, UpdateOrderStatusRequestDto requestDto);

    void completePurchase(ShoppingCart shoppingCart);
}
