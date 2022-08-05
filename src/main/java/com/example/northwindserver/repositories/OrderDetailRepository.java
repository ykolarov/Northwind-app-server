package com.example.northwindserver.repositories;

import com.example.northwindserver.entities.OrderDetail;
import com.example.northwindserver.entities.OrderDetailId;
import com.example.northwindserver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {


}