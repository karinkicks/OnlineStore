package ru.karinkicks.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karinkicks.entity.Cart;


@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
