package com.jacksspring.dscommerce.dto;

import com.jacksspring.dscommerce.entity.Order;
import com.jacksspring.dscommerce.entity.OrderItem;
import com.jacksspring.dscommerce.entity.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO cliennt;
    private PaymentDTO payment;
    @NotEmpty(message = "Deve possuir pelo menos um item")
    private List<OrderItemDTO> itens = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO cliennt, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.cliennt = cliennt;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.cliennt = new ClientDTO(entity.getClient());
        this.payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());

        for (OrderItem item : entity.getOrderItem()) {
            OrderItemDTO itemDTO = new OrderItemDTO(item);
            itens.add(itemDTO);

        }
    }
    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getCliennt() {
        return cliennt;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItens() {
        return itens;
    }

    public Double getTotal() {
        Double total = 0.0;
        for (OrderItemDTO subtotal : itens) {
            total += subtotal.getSubTotal();
        }
        return total;
    }
}
