package ru.karinkicks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.Role;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
