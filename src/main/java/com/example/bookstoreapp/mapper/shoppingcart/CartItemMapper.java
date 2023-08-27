package com.example.bookstoreapp.mapper.shoppingcart;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.shoppingcart.AddCartItemRequestDto;
import com.example.bookstoreapp.dto.response.shoppingcart.CartItemResponseDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
@RequiredArgsConstructor
public abstract class CartItemMapper {
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract CartItem toModel(AddCartItemRequestDto requestDto, Book book,
                                     ShoppingCart shoppingCart);

    @Mapping(target = "bookId", source = "cartItem.book.id")
    @Mapping(target = "bookTitle", source = "cartItem.book.title")
    public abstract CartItemResponseDto toDto(CartItem cartItem);
}
