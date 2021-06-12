package ru.karinkicks.tests.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.karinkicks.entity.Order;
import ru.karinkicks.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    void findAllTest() {
        final List<Order> all = orderRepository.findAll();
        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals("address", all.get(0).getAddress());
        Assertions.assertEquals(new BigDecimal("50.00"), all.get(0).getPrice());
        Assertions.assertEquals(1L, all.get(0).getId());
    }

    @Test
    void getOrderByIdTest() {
        final Order order = orderRepository.findById(1L).get();
        Assertions.assertEquals(1L, order.getId());
        Assertions.assertEquals("address", order.getAddress());
    }
}
