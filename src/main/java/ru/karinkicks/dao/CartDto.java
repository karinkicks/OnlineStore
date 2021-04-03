package ru.karinkicks.dao;

import ru.karinkicks.entity.Person;
import ru.karinkicks.entity.Product;

import java.util.List;

public class CartDto {
    private Long personId;
    private List<Product> product;

    public Long getPersonId() {
        return personId;
    }

    public void setPerson(Long personId) {
        this.personId = personId;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
