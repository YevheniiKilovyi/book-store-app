package com.example.bookstoreapp.mapper.order;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.response.order.OrderResponseDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.ShoppingCart;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class)
public abstract class OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "userId", source = "order.user.id")
    public abstract OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "status", expression = "java(com.example.bookstoreapp.model.Status.PENDING)")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    public abstract Order toOrderFromCart(ShoppingCart shoppingCart);

    @AfterMapping
    public void setOrderTotal(@MappingTarget Order order, ShoppingCart shoppingCart) {
        order.setTotal(getTotal(shoppingCart));
    }

    @AfterMapping
    public void setOrderItemsToDto(@MappingTarget OrderResponseDto orderResponseDto, Order order) {
        orderResponseDto.setOrderItems(
                order.getOrderItems().stream()
                        .map(orderItem -> orderItemMapper.toDto(orderItem))
                        .collect(Collectors.toSet()));
    }

    private BigDecimal getTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getBook)
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
