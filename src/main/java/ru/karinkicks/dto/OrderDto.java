package ru.karinkicks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.karinkicks.entity.Order;
import ru.karinkicks.entity.OrderItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto implements Serializable {
    private Long id;
    private String username;
    private String address;
    private BigDecimal price;
    private List<OrderItemDto> orderItems;
    private String creationDateTime;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getPerson().getUsername();
        this.address = order.getAddress();
        this.price = order.getPrice();
        this.creationDateTime = order.getCreatedAt().toString();
        this.orderItems = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(i -> this.orderItems.add(new OrderItemDto(i)));
    }
}
