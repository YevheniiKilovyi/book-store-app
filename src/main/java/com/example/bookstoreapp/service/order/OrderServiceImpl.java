package com.example.bookstoreapp.service.order;

import com.example.bookstoreapp.dto.request.order.PlaceOrderRequestDto;
import com.example.bookstoreapp.dto.request.order.UpdateOrderStatusRequestDto;
import com.example.bookstoreapp.dto.response.order.OrderItemResponseDto;
import com.example.bookstoreapp.dto.response.order.OrderResponseDto;
import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.mapper.order.OrderItemMapper;
import com.example.bookstoreapp.mapper.order.OrderMapper;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.OrderItem;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.repository.book.BookRepository;
import com.example.bookstoreapp.repository.order.OrderRepository;
import com.example.bookstoreapp.repository.shoppingcart.ShoppingCartRepository;
import com.example.bookstoreapp.service.shoppingcart.ShoppingCartService;
import com.example.bookstoreapp.service.user.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final ShoppingCartService shoppingCartService;
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public OrderResponseDto placeOrder(PlaceOrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartModel();

        Order order = orderMapper.toOrderFromCart(shoppingCart);
        order.setShippingAddress(requestDto.shippingAddress());
        Order savedOrder = orderRepository.save(order);

        Set<OrderItem> orderItems = getOrderItemsFromCart(shoppingCart);
        orderItems.forEach(orderItem -> orderItem.setOrder(savedOrder));
        savedOrder.setOrderItems(getSavedOrderItems(orderItems));
        completePurchase(shoppingCart);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public void updateOrderStatus(Long orderId, UpdateOrderStatusRequestDto requestDto) {
        Order orderById = getOrderById(orderId);
        orderById.setStatus(requestDto.status());
        orderRepository.save(orderById);
    }

    @Override
    public List<OrderResponseDto> getOrderHistory(Pageable pageable) {
        return orderRepository.getAllByUser(pageable, userService.getUser())
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderItemResponseDto> getOrderItems(Long orderId) {
        return getOrderById(orderId)
                .getOrderItems()
                .stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long orderId, Long itemId) {
        return orderItemService.findOrderItemByOrderIdAndId(orderId, itemId);
    }

    @Override
    public void completePurchase(ShoppingCart shoppingCart) {
        shoppingCartRepository.delete(shoppingCart);
        ShoppingCart shoppingCartNew = new ShoppingCart();
        shoppingCartNew.setUser(shoppingCart.getUser());
        shoppingCartRepository.save(shoppingCartNew);
    }

    private Set<OrderItem> getOrderItemsFromCart(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream()
                .map(cartItem -> orderItemMapper.cartItemToOrderItem(cartItem,
                        getBookFromCartItem(cartItem)))
                .collect(Collectors.toSet());
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Can't find order by id " + orderId));
    }

    private Set<OrderItem> getSavedOrderItems(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItemService::save)
                .collect(Collectors.toSet());
    }

    private Book getBookFromCartItem(CartItem cartItem) {
        return bookRepository.findById(cartItem.getBook().getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find book by id "
                        + cartItem.getBook().getId()));
    }
}
