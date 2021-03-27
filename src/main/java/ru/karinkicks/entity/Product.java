package ru.karinkicks.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="cost")
    private double cost;

    @ManyToMany
    @JoinTable(name = "person2product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Product(){}

    public Product(Long id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
}
