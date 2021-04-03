package ru.karinkicks.dao;

import org.springframework.stereotype.Component;
import ru.karinkicks.entity.Person;
import ru.karinkicks.entity.Product;

import java.util.*;

@Component
public class Cart {

    private final ProductRepository productRepository;
    private final PersonRepository personRepository;

    public Cart(ProductRepository productRepository, PersonRepository personRepository){
        this.productRepository = productRepository;
        this.personRepository = personRepository;
    }

    private final HashMap<Long, List<Product>> productsCart = new HashMap<>();

    public Product addProduct(Long idPerson, Long idProduct){
        Optional<Product> product = productRepository.findById(idProduct);
        Optional<Person> person = personRepository.findById(idPerson);
        List<Product> products;
        if(person.isPresent() && product.isPresent()){
            if(productsCart.containsKey(idPerson)){
                products =productsCart.get(idPerson);
            }else{
                products = new ArrayList<>();
            }
            products.add(product.get());
            productsCart.put(idPerson, products);
            return product.get();
        }else {
            throw new NoSuchElementException();
        }
    }

    public Product deleteProduct(Long idPerson, Long idProduct){
        Optional<Product> product = productRepository.findById(idProduct);
        Optional<Person> person = personRepository.findById(idPerson);

        if(person.isPresent() && product.isPresent() && productsCart.containsKey(idPerson)){
            List<Product> products = productsCart.get(idPerson);
            products.remove(product.get());
            productsCart.put(idPerson,products);
            return product.get();
        }else {
            throw new NoSuchElementException();
        }
    }

    public HashMap<Long, List<Product>> getProductCart(){
        return productsCart;
    }
}
