package ru.karinkicks.dao;

import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Person;
import ru.karinkicks.entity.Product;

import java.util.List;

@Service
public class PersonToProductDao {

    private final ProductDao productDao;
    private final PersonDao personDao;

    public PersonToProductDao(ProductDao productDao, PersonDao personDao){
        this.productDao = productDao;
        this.personDao=personDao;
    }

    public List<Person> getPersonsByProductId(Long id){
       return productDao.findById(id).get().getPersons();
    }

    public List<Product> getProductsByPersonId(Long id){
        return personDao.findById(id).get().getProducts();
    }

}
