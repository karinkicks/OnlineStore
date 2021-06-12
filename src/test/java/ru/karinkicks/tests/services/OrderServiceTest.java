package ru.karinkicks.tests.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.Order;
import ru.karinkicks.entity.Person;
import ru.karinkicks.repositories.OrderRepository;
import ru.karinkicks.services.CartService;
import ru.karinkicks.services.OrderService;
import ru.karinkicks.services.PersonService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = OrderService.class)
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    CartService cartService;

    @MockBean
    PersonService personService;

    @Test
    void findAllByOwner() {
        Person mockPerson = new Person();
        mockPerson.setUsername("Person1");
        mockPerson.setId(100L);
        mockPerson.setEmail("email@mail.ru");

        Cart mockCart = new Cart();
        mockCart.setPrice(BigDecimal.valueOf(50));
        mockCart.setPersonId(10L);

        Order mockOrder = new Order(mockCart, "address", mockPerson);
        mockOrder.setId(1L);

        Mockito
                .doReturn(Arrays.asList(mockOrder))
                .when(orderRepository)
                .findAllByPersonUsername("Person1");

        List<Order> list = orderService.findAllByOwner("Person1");
        Mockito.verify(orderRepository, Mockito.times(1))
                .findAllByPersonUsername(ArgumentMatchers.eq("Person1"));

        Assertions.assertEquals("address", list.get(0).getAddress());
        Assertions.assertEquals(1L, list.get(0).getId());
    }
}
