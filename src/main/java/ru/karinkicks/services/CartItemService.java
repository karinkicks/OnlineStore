package ru.karinkicks.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.CartItem;
import ru.karinkicks.repositories.CartItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {
    private CartItemRepository cartItemRepository;
    private ProductService productService;
    private CartService cartService;

    public Cart addCartItemToCart(Cart cart, Long productId) {
        CartItem cartItem = new CartItem(productService.findProductById(productId).orElseThrow());
        cartItemRepository.save(cartItem);
        cartService.addCartItem(cart,cartItem);
        cartService.updateCart(cart);
        return cart;
    }

    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    public CartItem saveOrUpdate(CartItem item) {
        return cartItemRepository.save(item);
    }

}
