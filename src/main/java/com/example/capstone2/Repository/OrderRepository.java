package com.example.capstone2.Repository;

import com.example.capstone2.Model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderHistory, Integer> {

    OrderHistory findOrderByOrderId(Integer orderId);

    List<OrderHistory> findOrdersByArtistId(Integer artistId);

    List<OrderHistory> findOrdersByUserId(Integer userId);
}
