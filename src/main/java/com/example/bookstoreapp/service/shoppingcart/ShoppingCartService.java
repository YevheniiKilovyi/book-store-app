package com.example.bookstoreapp.service.shoppingcart;

import com.example.bookstoreapp.dto.request.shoppingcart.AddCartItemRequestDto;
import com.example.bookstoreapp.dto.request.shoppingcart.UpdateBookQuantityInCartDto;
import com.example.bookstoreapp.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstoreapp.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartResponseDto getShoppingCart();

    ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto);

    ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                  UpdateBookQuantityInCartDto quantity);

    ShoppingCartResponseDto deleteCartItem(Long cartItemId);

    ShoppingCart getShoppingCartModel();
}
