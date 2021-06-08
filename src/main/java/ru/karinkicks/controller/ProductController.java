package ru.karinkicks.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.karinkicks.dto.ProductDto;
import ru.karinkicks.entity.Product;
import ru.karinkicks.services.ProductService;
import ru.karinkicks.specifications.ProductSpecifications;
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
    public Page<ProductDto> findAllProducts(@RequestParam MultiValueMap<String, String> params,
                                            @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) page = 1;
        return productService.findAll(ProductSpecifications.build(params), page, 4);
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
