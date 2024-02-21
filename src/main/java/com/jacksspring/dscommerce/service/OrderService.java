package com.jacksspring.dscommerce.service;
import com.jacksspring.dscommerce.dto.OrderDTO;
import com.jacksspring.dscommerce.entity.*;
import com.jacksspring.dscommerce.repository.OrderRepository;
import com.jacksspring.dscommerce.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso Não Encontrado")
        );


        return new OrderDTO(order);

    }


}