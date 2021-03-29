package ru.karinkicks.dao;

import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Product;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    private void init(){
        this.entityManager=entityManagerFactory.createEntityManager();
    }

    public Optional<Product> findById(Long id){
        Product p = null;
        try {
            p = (Product) entityManager.createQuery("SELECT a FROM Product a WHERE a.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException nre){
        }
        return (p==null) ? Optional.empty() : Optional.of(p);
    }

    public List<Product> findAll(){
        return entityManager.createQuery("SELECT a FROM Product a").getResultList();
    }

    public void deleteById(Long id){
        Optional<Product> p = findById(id);
        if(p.isPresent()) {
            entityManager.createQuery("DELETE FROM Product a WHERE a.id = :id").setParameter("id", id);
        }
        else{
            System.out.println("Продукта с таким ID нет в репозитории");
        }
    }

    @Transactional
    public void saveOrUpdate(Product product){
        if(findById(product.getId()).isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.createQuery("update Product set id = :id, name = :name, cost = :cost where id = :id")
                    .setParameter("id", product.getId())
                    .setParameter("name", product.getName())
                    .setParameter("cost", product.getCost())
                    .executeUpdate();
            entityManager.getTransaction().commit();
        }else {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("INSERT INTO product (id, name, cost) VALUES (?,?,?)")
                    .setParameter(1, product.getId())
                    .setParameter(2, product.getName())
                    .setParameter(3, product.getCost())
                    .executeUpdate();
            entityManager.getTransaction().commit();
        }
    }
}
