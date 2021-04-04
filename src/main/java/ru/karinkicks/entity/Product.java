package ru.karinkicks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty("Идентификатор товара")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty("Наименование товара")
    private String name;

    @Column(name="cost")
    @ApiModelProperty("Цена товара")
    private Double cost;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person2product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;
}
