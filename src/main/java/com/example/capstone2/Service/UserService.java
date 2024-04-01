package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.OrderHistory;
import com.example.capstone2.Model.Order_Rate;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.OrderRateRepository;
import com.example.capstone2.Repository.OrderRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final OrderRateRepository orderRateRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void updateUser(Integer userId, User user){
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("user does not exists");
        }

        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setRole(user.getRole());
        userRepository.save(u);
    }

    public void removeUser(Integer userId){
        boolean doesUserExists = userRepository.existsById(userId);
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        userRepository.deleteById(userId);
    }

    public void blockUser(Integer adminId, Integer userId){
        User admin = userRepository.findUserByUserId(adminId);
        if(admin == null){
            throw new ApiException("admin does not exists");
        }
        if(!admin.getRole().equals("admin")){
            throw new ApiException("user is not an admin");
        }
        User user = userRepository.findUserByUserId(userId);
        if(user == null){
            throw new ApiException("user does not exists");
        }
        if(user.getRole().equalsIgnoreCase("admin")){
            throw new ApiException("user is an admin");
        }
        user.setRole("blocked");
        userRepository.save(user);
    }

    public List<OrderHistory> getOrdersByUserId(Integer userId){
        boolean doesUserExists = userRepository.existsById(userId);
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        return orderRepository.findOrdersByUserId(userId);
    }

    public void orderRate(Integer userId, Integer orderId, Order_Rate rate) {
        User user = userRepository.findUserByUserId(userId);
        if(user == null){
            throw new ApiException("user does not exists");
        }
        OrderHistory orderHistory = orderRepository.findOrderByOrderId(orderId);
        if(orderHistory == null){
            throw new ApiException("order does not exists");
        }
        if(!orderHistory.getUserId().equals(userId)){
            throw new ApiException("order does not belong to user");
        }
        if(!orderHistory.getStatus().equals("done")){
            throw new ApiException("user can not rate unfinished order");
        }
        rate.setOrderId(orderId);
        orderRateRepository.save(rate);
    }

    public User loginCheck(String username, String password){
        User user = userRepository.findUserByUsername(username);

        if(user == null){
            throw new ApiException("invalid user name");
        }

        if(!user.getPassword().equals(password)){
            throw new ApiException("wrong password");
        }
        return user;
    }

}
