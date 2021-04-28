package ru.karinkicks.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.karinkicks.dto.ProductDto;
import ru.karinkicks.repositories.ProductRepository;
import ru.karinkicks.entity.Product;
import ru.karinkicks.services.ProductService;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @PostMapping( "/")
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("/")
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public List<ProductDto> findAllProducts(){
            return productService.findAll();
    }

    @GetMapping( "/{id}")
    public ProductDto getById(@PathVariable(value = "id") Long id) {
        return productService.findProductDtoById(id).orElseThrow(NoSuchElementException::new);
    }

    @DeleteMapping( "/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
