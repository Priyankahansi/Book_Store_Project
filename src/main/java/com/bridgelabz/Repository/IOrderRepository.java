package com.bridgelabz.Repository;

import com.bridgelabz.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails,Integer> {
}
