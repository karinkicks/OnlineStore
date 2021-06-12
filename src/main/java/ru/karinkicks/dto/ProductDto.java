package ru.karinkicks.dto;

import lombok.Data;
import ru.karinkicks.entity.Product;

import java.math.BigDecimal;


@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
