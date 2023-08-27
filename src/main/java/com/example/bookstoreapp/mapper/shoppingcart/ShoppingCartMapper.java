package com.example.bookstoreapp.mapper.shoppingcart;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.service.user.UserService;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class)
public abstract class ShoppingCartMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemMapper cartItemMapper;

    public abstract ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    @AfterMapping
    public void setBookInfoToCartDto(@MappingTarget ShoppingCartResponseDto responseDto,
                                     ShoppingCart shoppingCart) {
        responseDto.setCartItems(shoppingCart.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet()));
    }

    @AfterMapping
    public void setUserToShoppingCartDto(@MappingTarget ShoppingCartResponseDto cartResponseDto) {
        cartResponseDto.setUserId(userService.getUser().getId());
    }
}
