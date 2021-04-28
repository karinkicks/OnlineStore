package ru.karinkicks.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karinkicks.repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;


}
