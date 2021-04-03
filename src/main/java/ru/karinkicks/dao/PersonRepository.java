package ru.karinkicks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karinkicks.entity.Person;
import ru.karinkicks.entity.Product;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
