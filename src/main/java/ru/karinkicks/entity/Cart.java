package ru.karinkicks.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@RedisHash("cart")
public class Cart {

    @Id
    private Long personId;

    private BigDecimal price;

    private List<CartItem> cartItems;

    public Cart(Long personId){
        this.personId = personId;
    }

    public  Cart(){
        this.cartItems=new ArrayList<>();
    }

//    @CreationTimestamp
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @ManyToOne
//    @JoinColumn(name = "person_id")
//    private Person person;
//

//
//    public Optional<CartItem> getCartItemFromProductId(Long productId) {
//        if (!cartItems.isEmpty()) {
//            for (CartItem cartItem : cartItems) {
//                if (cartItem.getProduct().getId().equals(productId)) {
//                    return Optional.of(cartItem);
//                }
//            }
//        }
//        return Optional.empty();
//    }
//
//    public void changePrice(){
//        price = cartItems.stream()
//                .map(CartItem::getPrice)
//                .reduce(Double::sum).orElse((double) 0);
//    }
//
//    public void addItem(CartItem cartItem){
//        this.cartItems.add(cartItem);
//        cartItem.setCart(this);
//        changePrice();
//    }
//
//    public void deleteItem(CartItem cartItem){
//        this.cartItems.remove(cartItem);
//        cartItem.setCart(this);
//        changePrice();
//    }
}
