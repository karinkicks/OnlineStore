package ru.karinkicks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karinkicks.entity.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
