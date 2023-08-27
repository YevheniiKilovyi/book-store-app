package com.example.bookstoreapp.service.shoppingcart;

import com.example.bookstoreapp.dto.request.shoppingcart.AddCartItemRequestDto;
import com.example.bookstoreapp.model.CartItem;

public interface CartItemService {
    CartItem save(AddCartItemRequestDto requestDto);

    CartItem findById(Long id);

    void deleteById(Long cartItemId);
}
