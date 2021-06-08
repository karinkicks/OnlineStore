package ru.karinkicks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@RedisHash("cart_item")
@NoArgsConstructor
public class CartItem {
    @Id
    private Long id;

    @Indexed
    private Cart cart;

    private Product product;

    private String name;

    private Integer quantity;

    private BigDecimal pricePerProduct;

    private BigDecimal price;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.name = product.getName();
        this.pricePerProduct = product.getPrice();
        this.price = this.pricePerProduct;
    }

//    public void changeQuantityAndPrice(){
//        quantity++;
//        price=quantity*pricePerProduct;
//    }
//    @CreationTimestamp
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
}
