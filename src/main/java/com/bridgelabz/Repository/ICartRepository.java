package com.bridgelabz.Repository;

import com.bridgelabz.Model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartDetails,Integer> {

}
