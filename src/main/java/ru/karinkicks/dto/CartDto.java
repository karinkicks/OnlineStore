package ru.karinkicks.dto;

import lombok.Data;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.CartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartDto {
    private List<CartItemDto> cartItemDtoList;
    private BigDecimal price;

    public CartDto(Cart cart) {
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.stream().map(CartItemDto::new);
        this.cartItemDtoList = cartItemDtoList;
        this.price = cart.getPrice();
    }
}
