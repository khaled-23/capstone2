package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Order_Rate;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.addUser(user);
        logger.info("user added");
        return ResponseEntity.ok(new ApiResponse("user added"));
    }


    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        logger.info("all user requested");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId,@RequestBody @Valid User user){
        userService.updateUser(userId, user);
        logger.info("user updated");
        return ResponseEntity.ok(new ApiResponse("user updated"));
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity removeUser(@PathVariable Integer userId){
        userService.removeUser(userId);
        logger.info("user removed");
        return ResponseEntity.ok(new ApiResponse("user removed"));
    }

    @GetMapping("/view-orders/{userId}")
    public ResponseEntity viewUserOrders(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getOrdersByUserId(userId));
    }
    @PostMapping("/order/rate/{userId}/{orderId}")
    public ResponseEntity order(@PathVariable Integer userId,@PathVariable Integer orderId, @RequestBody @Valid Order_Rate rate){
        userService.orderRate(userId,orderId,rate);
        logger.info("user rated an order");
        return ResponseEntity.ok(new ApiResponse("rated"));
    }

    @PutMapping("/admin/block/{adminId}/{userId}")
    public ResponseEntity blockUser(@PathVariable Integer adminId,@PathVariable Integer userId){
        userService.blockUser(adminId,userId);
        logger.info("admin blocked a user");
        return ResponseEntity.ok(new ApiResponse("user blocked"));
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username,@PathVariable String password){
        logger.info("check user credentials");
        return ResponseEntity.ok(userService.loginCheck(username,password));
    }


}
