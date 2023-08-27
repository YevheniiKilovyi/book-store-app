package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.request.order.PlaceOrderRequestDto;
import com.example.bookstoreapp.dto.request.order.UpdateOrderStatusRequestDto;
import com.example.bookstoreapp.dto.response.order.OrderItemResponseDto;
import com.example.bookstoreapp.dto.response.order.OrderResponseDto;
import com.example.bookstoreapp.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Retrieve user's order history (USER)")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the order history")
    public List<OrderResponseDto> getOrderHistory(@ParameterObject Pageable pageable) {
        return orderService.getOrderHistory(pageable);
    }

    @PostMapping
    @Operation(summary = "Place an order (USER)")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Successfully placed an order")
    @ApiResponse(responseCode = "400", description = "Bad request")
    public OrderResponseDto placeOrder(@RequestBody PlaceOrderRequestDto requestDto) {
        return orderService.placeOrder(requestDto);
    }

    @PatchMapping("/{orderId}")
    @Operation(summary = "Update order status (ADMIN)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(responseCode = "202", description = "Order status updated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public void updateOrderStatus(@PathVariable Long orderId,
                                  @RequestBody UpdateOrderStatusRequestDto requestDto) {
        orderService.updateOrderStatus(orderId, requestDto);
    }

    @GetMapping("/{orderId}/items")
    @Operation(summary = "Retrieve all order items for a specific order (USER)")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved order items")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public List<OrderItemResponseDto> getAllOrderItems(@PathVariable Long orderId) {
        return orderService.getOrderItems(orderId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Retrieve a specific order item within an order (USER)")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the order item")
    @ApiResponse(responseCode = "404", description = "Order or item not found")
    public OrderItemResponseDto getOrderItemById(@PathVariable Long orderId,
                                                 @PathVariable Long itemId) {
        return orderService.getOrderItem(orderId, itemId);
    }
}
