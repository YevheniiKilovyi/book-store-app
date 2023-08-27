package com.example.bookstoreapp.repository.order;

import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderById(Long id);

    List<Order> getAllByUser(Pageable pageable, User user);
}
