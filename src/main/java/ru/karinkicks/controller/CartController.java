package ru.karinkicks.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.karinkicks.dto.CartDto;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.Person;
import ru.karinkicks.services.CartItemService;
import ru.karinkicks.services.CartService;
import ru.karinkicks.services.PersonService;
import ru.karinkicks.services.ProductService;
import java.security.Principal;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final PersonService personService;
    private final CartItemService cartItemService;

    @GetMapping
    public Cart getCurrentCart(Principal principal) {
        Person person = personService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return cartService.findCartByOwnerId(person.getId());
    }

    @PostMapping
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }

    @DeleteMapping
    public Cart clearCart(Principal principal) {
        Person person = personService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return cartService.clearCart(person.getId());
    }

    @GetMapping("/{id}")
    public Cart addProductToCart(@PathVariable Long id, Principal principal) {
        Person person = personService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return cartItemService.addCartItemToCart(cartService.findCartByOwnerId(person.getId()), id);
    }

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
