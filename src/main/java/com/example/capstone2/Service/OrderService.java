package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Artist;
import com.example.capstone2.Model.OrderHistory;
import com.example.capstone2.Model.Order_Rate;
import com.example.capstone2.Repository.ArtistRepository;
import com.example.capstone2.Repository.OrderRateRepository;
import com.example.capstone2.Repository.OrderRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final OrderRateRepository orderRateRepository;


    public void addOrder(OrderHistory orderHistory){
        Artist artist = artistRepository.findArtistByArtistId(orderHistory.getArtistId());
        if(artist == null){
            throw new ApiException("artist does not exists");
        }
        if(!artist.getDoesAcceptOrder()){
            throw new ApiException("artist does not accept offer right now");
        }
        boolean doesUserExists = userRepository.existsById(orderHistory.getUserId());
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        orderHistory.setOrderDate(LocalDate.now());
        orderHistory.setStatus("undone");
        orderRepository.save(orderHistory);
    }

    public List<OrderHistory> getAllOrders(){
        return orderRepository.findAll();
    }

    public void updateOrder(Integer orderId, OrderHistory orderHistory){
        OrderHistory o = orderRepository.findOrderByOrderId(orderId);
        if(o==null){
            throw new ApiException("order does not exists");
        }
        o.setStatus(orderHistory.getStatus());
        o.setPrice(orderHistory.getPrice());
        o.setArtistId(orderHistory.getArtistId());
        o.setUserId(orderHistory.getUserId());
        orderRepository.save(o);
    }

    public void removeOrder(Integer orderId){
        OrderHistory orderHistory = orderRepository.findOrderByOrderId(orderId);
        if(orderHistory ==null){
            throw new ApiException("order does not exists");
        }
        orderRepository.delete(orderHistory);
    }

    public List<Order_Rate> getAllOrderRate(){
        return orderRateRepository.findAll();
    }



}
