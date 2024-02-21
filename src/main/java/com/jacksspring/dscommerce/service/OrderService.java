package com.jacksspring.dscommerce.service;
import com.jacksspring.dscommerce.dto.OrderDTO;
import com.jacksspring.dscommerce.dto.OrderItemDTO;
import com.jacksspring.dscommerce.entity.*;
import com.jacksspring.dscommerce.repository.OrderItemRepository;
import com.jacksspring.dscommerce.repository.OrderRepository;
import com.jacksspring.dscommerce.repository.ProductRepository;
import com.jacksspring.dscommerce.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;



    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso Não Encontrado")
        );


        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);

    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {

        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO dto : orderDTO.getItens()) {
            Product product = productRepository.getReferenceById(dto.getProductId());
            OrderItem item = new OrderItem(order, product, dto.getQuantity(), product.getPrice());
            order.getItens().add(item);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItens());

        return new OrderDTO(order);

    }

}