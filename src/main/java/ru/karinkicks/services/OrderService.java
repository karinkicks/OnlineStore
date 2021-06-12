package ru.karinkicks.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.Order;
import ru.karinkicks.entity.Person;
import ru.karinkicks.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private PersonService personService;
    private CartService cartService;

    @Transactional
    public Order createFromUserCart(String username, String address) {
        Person person = personService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartService.findCartByOwnerId(person.getId());
        if (cart.getCartItems().isEmpty()) throw new RuntimeException("Cart is empty");
        Order order = new Order(cart, address, person);
        order = orderRepository.save(order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllByOwner(String username) {
        return orderRepository.findAllByPersonUsername(username);
    }
}
