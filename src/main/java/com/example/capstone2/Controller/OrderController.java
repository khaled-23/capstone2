package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.OrderHistory;
import com.example.capstone2.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid OrderHistory orderHistory, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderService.addOrder(orderHistory);
        return ResponseEntity.ok(new ApiResponse("order added"));
    }
    @GetMapping("/orders")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@PathVariable Integer orderId,@RequestBody @Valid OrderHistory orderHistory, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderService.updateOrder(orderId, orderHistory);
        return ResponseEntity.ok("order updated");
    }
    @DeleteMapping("/remove/{orderId}")
    public ResponseEntity removeOrder(@PathVariable Integer orderId){
        orderService.removeOrder(orderId);
        return ResponseEntity.ok("order removed");
    }

    @GetMapping("/orders-rate")
    public ResponseEntity ordersRate(){
        return ResponseEntity.ok(orderService.getAllOrderRate());
    }


}
