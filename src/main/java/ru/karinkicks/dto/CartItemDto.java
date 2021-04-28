package ru.karinkicks.dto;

import lombok.Data;
import ru.karinkicks.entity.CartItem;
import ru.karinkicks.entity.OrderItem;

@Data
public class CartItemDto {
    private Long productId;
    private String productName;
    private Double pricePerProduct;
    private Double price;
    private Integer quantity;

    public CartItemDto(CartItem cartItem) {
        this.productId = cartItem.getProduct().getId();
        this.productName = cartItem.getName();
        this.pricePerProduct = cartItem.getPricePerProduct();
        this.price = cartItem.getPrice();
        this.quantity = cartItem.getQuantity();
    }
}
