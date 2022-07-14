package com.bridgelabz.service;

import com.bridgelabz.DTO.OrderDTO;
import com.bridgelabz.Exception.UserException;
import com.bridgelabz.Model.BookDetails;
import com.bridgelabz.Model.CartDetails;
import com.bridgelabz.Model.OrderDetails;
import com.bridgelabz.Model.UserModel;
import com.bridgelabz.Repository.IBookDetailsRepository;
import com.bridgelabz.Repository.ICartRepository;
import com.bridgelabz.Repository.IOrderRepository;
import com.bridgelabz.Repository.IUserRepository;
import com.bridgelabz.Util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepo;
    @Autowired
    IBookDetailsRepository bookRepo;
    @Autowired
    IUserRepository userRepo;
    @Autowired
    EmailSenderService sender;

    @Override
    public String getMessage() {
        return
                "Welcome to BookStore";
    }

    @Override
    public OrderDetails addToOrder(OrderDTO orderDTO) {
        Optional<UserModel> isUserPresent = userRepo.findById(orderDTO.getUserId());
        if (isUserPresent.isPresent()) {
            BookDetails book = bookRepo.findById(orderDTO.getBookId()).orElseThrow();
            OrderDetails orderDetail = new OrderDetails(isUserPresent.get(), book, orderDTO);
            sender.sendEmail(isUserPresent.get().getEmail(),"Test Mail",isUserPresent.get().getFirstName()+
                    "  " +"Your Order has been placed Successfully"+ "\n" + book.toString());
            return orderRepo.save(orderDetail);
        } else throw new UserException("Invalid User");
    }


    @Override
    public Optional<OrderDetails> getOrderById(int getOrderId) {
        Optional<OrderDetails> orderDetails = orderRepo.findById(getOrderId);
        if (orderDetails.isPresent()) {
            return orderDetails;
        } else {
            throw new UserException("Order Id not found");
        }
    }

    @Override
    public List<OrderDetails> getListOfOrderDetails() {
        List<OrderDetails> order= orderRepo.findAll();
        return order;
    }

    @Override
    public OrderDetails updateOrder(int getId, OrderDTO orderDTO) {
            Optional<OrderDetails> order =orderRepo.findById(getId);
                order.get().setQuantity(orderDTO.getQuantity());
                order.get().setAddress(orderDTO.getAddress());
                order.get().setPrice(orderDTO.getPrice());
                order.get().setCancel(orderDTO.isCancel());
                orderRepo.save(order.get());
                return order.get();
    }

    @Override
    public void deleteBookFromOrder(int orderId) {
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<OrderDetails> deleteAll() {
        orderRepo.deleteAll();
        return null;
    }
}
