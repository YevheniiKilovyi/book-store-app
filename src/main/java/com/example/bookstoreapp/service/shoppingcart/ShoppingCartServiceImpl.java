package com.example.bookstoreapp.service.shoppingcart;

import com.example.bookstoreapp.dto.request.shoppingcart.AddCartItemRequestDto;
import com.example.bookstoreapp.dto.request.shoppingcart.UpdateBookQuantityInCartDto;
import com.example.bookstoreapp.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstoreapp.mapper.shoppingcart.ShoppingCartMapper;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.shoppingcart.ShoppingCartRepository;
import com.example.bookstoreapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCartResponseDto getShoppingCart() {
        return shoppingCartMapper.toDto(getShoppingCartModel());
    }

    @Override
    public ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = getShoppingCartModel();
        CartItem cartItem = cartItemService.save(requestDto);
        shoppingCart.addCartItem(cartItem);
        return getShoppingCart();
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                         UpdateBookQuantityInCartDto requestDto) {
        cartItemService.findById(cartItemId).setQuantity(requestDto.quantity());
        return getShoppingCart();
    }

    @Override
    public ShoppingCartResponseDto deleteCartItem(Long cartItemId) {
        cartItemService.deleteById(cartItemId);
        return getShoppingCart();
    }

    @Override
    public ShoppingCart getShoppingCartModel() {
        User user = userService.getUser();
        return shoppingCartRepository.findById(user.getId()).get();
    }
}
