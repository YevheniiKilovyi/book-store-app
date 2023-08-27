package com.example.bookstoreapp.mapper.order;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.response.order.OrderItemResponseDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public abstract class OrderItemMapper {

    @Mapping(target = "bookId", source = "orderItem.book.id")
    public abstract OrderItemResponseDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", source = "book.price")
    @Mapping(target = "deleted", ignore = true)
    public abstract OrderItem cartItemToOrderItem(CartItem cartItem, Book book);
}
