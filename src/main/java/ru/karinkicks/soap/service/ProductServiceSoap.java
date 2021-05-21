package ru.karinkicks.soap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Product;
import ru.karinkicks.repositories.ProductRepository;
import ru.karinkicks.soap.ProductSoap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceSoap {
    private final ProductRepository productRepository;

    public static final Function<Product, ProductSoap> functionEntityToSoap = se -> {
        ProductSoap p = new ProductSoap();
        p.setId(se.getId());
        p.setName(se.getName());
        p.setPrice(se.getPrice());
        p.setCreatedAt(se.getCreatedAt());
        p.setUpdatedAt(se.getUpdatedAt());
        return p;
    };

    public List<ProductSoap> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap)
                .collect(Collectors.toList());

    }

    public ProductSoap getById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }
}
