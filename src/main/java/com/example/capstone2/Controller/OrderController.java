package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.OrderHistory;
import com.example.capstone2.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid OrderHistory orderHistory){
        orderService.addOrder(orderHistory);
        logger.info("order added");
        return ResponseEntity.ok(new ApiResponse("order added"));
    }
    @GetMapping("/orders")
    public ResponseEntity getAllOrders(){
        logger.info("get all order");
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@PathVariable Integer orderId,@RequestBody @Valid OrderHistory orderHistory) {
        orderService.updateOrder(orderId, orderHistory);
        logger.info("order updated");
        return ResponseEntity.ok("order updated");
    }
    @DeleteMapping("/remove/{orderId}")
    public ResponseEntity removeOrder(@PathVariable Integer orderId){
        orderService.removeOrder(orderId);
        logger.info("order removed");
        return ResponseEntity.ok("order removed");
    }

    @GetMapping("/orders-rate")
    public ResponseEntity ordersRate(){
        logger.info("order rate");
        return ResponseEntity.ok(orderService.getAllOrderRate());
    }


}
