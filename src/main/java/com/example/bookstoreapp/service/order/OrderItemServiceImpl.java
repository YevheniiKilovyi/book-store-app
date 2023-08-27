package com.example.bookstoreapp.service.order;

import com.example.bookstoreapp.dto.response.order.OrderItemResponseDto;
import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.mapper.order.OrderItemMapper;
import com.example.bookstoreapp.model.OrderItem;
import com.example.bookstoreapp.repository.order.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId, Long itemId) {
        return orderItemMapper.toDto(orderItemRepository
                .findOrderItemByOrderIdAndId(orderId, itemId).orElseThrow(() ->
                        new EntityNotFoundException("Can't find item with order id "
                                + orderId + " and item id " + itemId)));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
