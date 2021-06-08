package ru.karinkicks.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.karinkicks.dto.OrderDto;
import ru.karinkicks.entity.Order;
import ru.karinkicks.entity.Person;
import ru.karinkicks.rabbit_mq.Sender;
import ru.karinkicks.services.CartService;
import ru.karinkicks.services.OrderService;
import ru.karinkicks.services.PersonService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final PersonService personService;
    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(Principal principal, @RequestParam String address) {
        Order order = orderService.createFromUserCart(principal.getName(), address);
        Person person = personService.findByUsername(principal.getName()).orElseThrow();
        cartService.clearCart(person.getId());
        OrderDto orderDto = new OrderDto(order);
        Sender.send(orderDto);
        return orderDto;
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return new OrderDto(order);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllByOwner(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
