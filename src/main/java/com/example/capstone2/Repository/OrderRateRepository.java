package com.example.capstone2.Repository;

import com.example.capstone2.Model.Order_Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRateRepository extends JpaRepository<Order_Rate, Integer> {

    Order_Rate findOrder_RateByOrderId(Integer orderId);
}
