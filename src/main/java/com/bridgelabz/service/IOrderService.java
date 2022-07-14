package com.bridgelabz.service;

import com.bridgelabz.DTO.OrderDTO;
import com.bridgelabz.Model.CartDetails;
import com.bridgelabz.Model.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    String getMessage();


    OrderDetails addToOrder(OrderDTO orderDTO);

    Optional<OrderDetails> getOrderById(int getOrderId);

    List<OrderDetails> getListOfOrderDetails();

    OrderDetails updateOrder(int getId, OrderDTO orderDTO);

    void deleteBookFromOrder(int orderId);


    List<OrderDetails> deleteAll();

}
