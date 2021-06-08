package ru.karinkicks.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.karinkicks.entity.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    /*List<Product> findByCostLessThanEqual(Double cost);
    List<Product> findByCostGreaterThanEqual(Double cost);
    List<Product> findByCostBetween(Double cost, Double cost2);*/

}
