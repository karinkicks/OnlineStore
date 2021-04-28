package ru.karinkicks.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Cart(Person person){
        this.person = person;
    }

    public  Cart(){

    }

    public Optional<CartItem> getCartItemFromProductId(Long productId) {
        if (!cartItems.isEmpty()) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId().equals(productId)) {
                    return Optional.of(cartItem);
                }
            }
        }
        return Optional.empty();
    }

    public void changePrice(){
        price = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(Double::sum).orElse((double) 0);
    }

    public void addItem(CartItem cartItem){
        this.cartItems.add(cartItem);
        cartItem.setCart(this);
        changePrice();
    }

    public void deleteItem(CartItem cartItem){
        this.cartItems.remove(cartItem);
        cartItem.setCart(this);
        changePrice();
    }
}
