package ru.karinkicks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.karinkicks.dao.ProductRepository;
import ru.karinkicks.entity.Product;

import java.util.Optional;

@Controller
public class ProductRepositoryController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryController(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @GetMapping( "/products")
    public String getForm(Model uiModel){
        uiModel.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/products")
    public String create(Product product) {
        productRepository.save(product);
        return "add_product";
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

    @GetMapping( "/products/{id}")
    public String findById(Model model, @PathVariable(value = "id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.get());
        return "all_products";
    }

    @GetMapping( "/products/delete/{id}")
    public String deleteById(Model model, @PathVariable(value = "id") Long id) {
        productRepository.deleteById(id);
        return "all_products";
    }

}
