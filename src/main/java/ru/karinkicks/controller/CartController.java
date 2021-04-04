package ru.karinkicks.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.karinkicks.repositories.CartRepository;
import ru.karinkicks.dao.CartDto;
import ru.karinkicks.entity.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartRepository cart;

    public CartController(CartRepository cart) {
        this.cart = cart;
    }

    @ApiOperation("Добавление продукта в корзину покупателя")
    @PostMapping("/{personId}/{productId}")
    public Product addProduct(@PathVariable Long personId, @PathVariable Long productId){
        return cart.addProduct(personId, productId);
    }

    @ApiOperation("Просмотр всех имеющихся корзин")
    @GetMapping
    @ResponseBody
    public List<CartDto> getProducts(){
        List<CartDto> cartDtos = new ArrayList<>();
        cart.getProductCart().forEach((person, products) -> {
            CartDto cartDto = new CartDto();
            cartDto.setPerson(person);
            cartDto.setProduct(products);
            cartDtos.add(cartDto);
        });
        return cartDtos;
    }

    @ApiOperation("Просмотр корзины по идентификатору покупателя")
    @GetMapping("/{id}")
    @ResponseBody
    public List<CartDto> getProductsByIdPerson(@PathVariable Long id){
        List<CartDto> cartDtos = new ArrayList<>();
        cart.getProductCart().forEach((person, products) -> {
            if(person.equals(id)) {
                CartDto cartDto = new CartDto();
                cartDto.setPerson(person);
                cartDto.setProduct(products);
                cartDtos.add(cartDto);
            }
        });
        return cartDtos;
    }
    @ApiOperation("Удаление продукта из корзины покупателя")
    @DeleteMapping("/{personId}/{productId}")
    public Product deleteProduct(@PathVariable Long personId, @PathVariable Long productId){
        return cart.deleteProduct(personId, productId);
    }
}
