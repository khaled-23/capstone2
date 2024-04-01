package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Order_Rate;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("user added"));
    }


    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
         return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        userService.updateUser(userId, user);
        return ResponseEntity.ok("user updated");
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity removeUser(@PathVariable Integer userId){
        userService.removeUser(userId);
        return ResponseEntity.ok("user removed");
    }

    @GetMapping("/view-orders/{userId}")
    public ResponseEntity viewUserOrders(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getOrdersByUserId(userId));
    }
    @PostMapping("/order/rate/{userId}/{orderId}")
    public ResponseEntity order(@PathVariable Integer userId,@PathVariable Integer orderId, @RequestBody @Valid Order_Rate rate){
        userService.orderRate(userId,orderId,rate);
        return ResponseEntity.ok(new ApiResponse("rated"));
    }

    @PutMapping("/admin/block/{adminId}/{userId}")
    public ResponseEntity blockUser(@PathVariable Integer adminId,@PathVariable Integer userId){
        userService.blockUser(adminId,userId);
        return ResponseEntity.ok("user blocked");
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username,@PathVariable String password){
        return ResponseEntity.ok(userService.loginCheck(username,password));
    }


}
