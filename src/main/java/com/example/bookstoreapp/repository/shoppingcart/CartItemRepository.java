package com.example.bookstoreapp.repository.shoppingcart;

import com.example.bookstoreapp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
