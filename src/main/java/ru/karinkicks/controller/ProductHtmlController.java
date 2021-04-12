package ru.karinkicks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.karinkicks.repositories.CartRepository;
import ru.karinkicks.repositories.PersonRepository;
import ru.karinkicks.repositories.ProductRepository;
import ru.karinkicks.entity.Product;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductHtmlController {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final PersonRepository personRepository;

    public ProductHtmlController(ProductRepository productRepository, CartRepository cartRepository, PersonRepository personRepository){
        this.productRepository=productRepository;
        this.cartRepository = cartRepository;
        this.personRepository = personRepository;
    }



    @GetMapping
    public String getForm(Model uiModel){
        uiModel.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping
    public String create(Product product) {
        productRepository.save(product);
        return "redirect:/all_products";
    }

    @GetMapping( "/all_products")
    public String showProductRepository(Model uiModel,
                                        @RequestParam(required = false, name = "min") Double min,
                                        @RequestParam(required = false, name = "max") Double max){

        if(min == null && max == null){
            uiModel.addAttribute("products", productRepository.findAll());
        }else if (min ==null && max != null){
            uiModel.addAttribute("products", productRepository.findByCostLessThanEqual(max));
        }else if(min != null && max == null){
            uiModel.addAttribute("products", productRepository.findByCostGreaterThanEqual(min));
        }else {
            uiModel.addAttribute("products", productRepository.findByCostBetween(min, max));
        }
        return "all_products";
    }

    @GetMapping( "/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.get());
        return "redirect:/all_products";
    }

    @GetMapping( "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/all_products";
    }


}
