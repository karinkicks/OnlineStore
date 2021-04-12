package ru.karinkicks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.karinkicks.entity.Product;
import ru.karinkicks.repositories.CartRepository;
import ru.karinkicks.repositories.PersonRepository;
import ru.karinkicks.repositories.ProductRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class CartHtmlController {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final PersonRepository personRepository;

    public CartHtmlController(ProductRepository productRepository, CartRepository cartRepository, PersonRepository personRepository){
        this.productRepository=productRepository;
        this.cartRepository = cartRepository;
        this.personRepository = personRepository;
    }


    @GetMapping( "/add_in_cart/{id}")
    public String addInCart(@PathVariable Long id, Principal principal) {
        cartRepository.addProduct(personRepository.findByUsername(principal.getName()).getId(), id);
        cartRepository.getProductCart().get(personRepository.findByUsername(principal.getName()).getId());
        return "redirect:/all_products_in_cart";
    }

    @GetMapping( "/all_products_in_cart")
    public String allProductsInCart(Model uiModel, Principal principal) {
        List<Product> products = cartRepository.getProductCart().get(personRepository.findByUsername(principal.getName()).getId());
        uiModel.addAttribute("products", products);
        return "cart";
    }

    @GetMapping( "/delete_in_cart/{id}")
    public String deleteById(@PathVariable Long id, Principal principal) {
        cartRepository.deleteProduct(personRepository.findByUsername(principal.getName()).getId(), id);
        return "redirect:/all_products_in_cart";
    }

}
