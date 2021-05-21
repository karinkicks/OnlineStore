package ru.karinkicks.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.karinkicks.dto.CartDto;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.services.CartService;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
//    private final CartService cartService;
//
//    @GetMapping("/{id}")
//    public CartDto getCartById(@PathVariable Long id) {
//        Cart cart = cartService.findCartById(id).orElseThrow(NoSuchElementException::new);
//        return new CartDto(cart);
//    }
//
//    @GetMapping("/add/{productId}")
//    public void addProductToCart(@PathVariable Long productId, Principal principal) {
//        cartService.addProductToCartById(cartService.getIdCartFromUsername(principal.getName()), productId);
//    }
//    @DeleteMapping("/add/{productId}") //Request method 'DELETE' not supported ??
//    public void deleteProductFromCart(@PathVariable Long productId, Principal principal){
//        cartService.deleteProductFromCartById(cartService.getIdCartFromUsername(principal.getName()), productId);
//    }

}
