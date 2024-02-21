package com.jacksspring.dscommerce.repository;

import com.jacksspring.dscommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long > {

}
