package ru.karinkicks.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karinkicks.dao.ProductRepository;
import ru.karinkicks.entity.Product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRepositoryController {

    private final ProductRepository productRepository;

    public ProductRepositoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping( "/")
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/")
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping( "/all_products")
    public List<Product> showProductRepository(Model uiModel,
                                        @RequestParam(required = false, name = "min") Double min,
                                        @RequestParam(required = false, name = "max") Double max){
        if(min == null && max == null){
            return productRepository.findAll();
        }else if (min == null){
            return productRepository.findByCostLessThanEqual(max);
        }else if(max == null){
            return productRepository.findByCostGreaterThanEqual(min);
        }else {
            return productRepository.findByCostBetween(min, max);
        }
    }

    @GetMapping( "/{id}")
    public Product getById(Model model, @PathVariable(value = "id") Long id) {
        return productRepository.findById(id).get();
    }

    @DeleteMapping( "/{id}")
    public void deleteById(@PathVariable Long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

}
