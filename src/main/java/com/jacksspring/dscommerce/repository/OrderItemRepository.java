package com.jacksspring.dscommerce.repository;

import com.jacksspring.dscommerce.entity.OrderItem;
import com.jacksspring.dscommerce.entity.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
