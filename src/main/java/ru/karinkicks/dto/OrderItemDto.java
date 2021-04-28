package ru.karinkicks.dto;

import lombok.Data;
import ru.karinkicks.entity.OrderItem;

@Data
public class OrderItemDto {
    private Long productId;
    private String productName;
    private Double pricePerProduct;
    private Double price;
    private Integer quantity;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productName = orderItem.getName();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
    }
}
