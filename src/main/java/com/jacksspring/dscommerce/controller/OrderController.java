package com.jacksspring.dscommerce.controller;

import com.jacksspring.dscommerce.dto.OrderDTO;
import com.jacksspring.dscommerce.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {

        OrderDTO dto = orderService.findById(id);
        return ResponseEntity.ok(dto);
    }





}
