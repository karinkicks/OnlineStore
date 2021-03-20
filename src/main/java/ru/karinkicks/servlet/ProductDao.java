package ru.karinkicks.servlet;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Product;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDao {

    private final EntityManagerService entityManagerService;

    public ProductDao(EntityManagerService entityManagerService){
        this.entityManagerService=entityManagerService;
    }

    public Optional<Product> findById(Long id){
        Product p = null;
        try {
            p = (Product) entityManagerService.em.createQuery("SELECT a FROM Product a WHERE a.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException nre){
        }
        return (p==null) ? Optional.empty() : Optional.of(p);
    }

    public List<Product> findAll(){
        return entityManagerService.em.createQuery("SELECT a FROM Product a").getResultList();
    }

    public void deleteById(Long id){
        Optional<Product> p = findById(id);
        if(p.isPresent()) {
            entityManagerService.em.createQuery("DELETE FROM Product a WHERE a.id = :id").setParameter("id", id);
        }
        else{
            System.out.println("Продукта с таким ID нет в репозитории");
        }
    }

    @Transactional
    public void saveOrUpdate(Product product){
        if(findById(product.getId()).isPresent()) {
            entityManagerService.em.getTransaction().begin();
            entityManagerService.em.createQuery("update Product set id = :id, name = :name, cost = :cost where id = :id")
                    .setParameter("id", product.getId())
                    .setParameter("name", product.getName())
                    .setParameter("cost", product.getCost())
                    .executeUpdate();
            entityManagerService.em.getTransaction().commit();
        }else {
            entityManagerService.em.getTransaction().begin();
            entityManagerService.em.createNativeQuery("INSERT INTO product (id, name, cost) VALUES (?,?,?)")
                    .setParameter(1, product.getId())
                    .setParameter(2, product.getName())
                    .setParameter(3, product.getCost())
                    .executeUpdate();
            entityManagerService.em.getTransaction().commit();
        }
    }
}
