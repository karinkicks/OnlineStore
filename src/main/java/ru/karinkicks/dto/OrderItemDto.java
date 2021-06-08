package ru.karinkicks.dto;

import lombok.Data;
import ru.karinkicks.entity.OrderItem;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDto implements Serializable {
    private Long productId;
    private String productName;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private Integer quantity;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productName = orderItem.getName();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
    }
}
