package ru.karinkicks.servlet;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.karinkicks.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**Создайте класс ProductDao и реализуйте в нем логику выполнения
CRUD-операций над сущностью Product (Product findById(Long id)(используя jpql),
List"Product" findAll()(используя jpql), void deleteById(Long id),
 Product saveOrUpdate(Product product)).**/
@Component
public class ProductDao {
    EntityManagerFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
    EntityManager em = factory.createEntityManager();

    public Optional<Product> findById(Long id){
        Product p = null;
        try {
            p = (Product) em.createQuery("SELECT a FROM Product a WHERE a.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException nre){
        }
        return (p==null) ? Optional.empty() : Optional.of(p);
    }

    public List<Product> findAll(){
        return em.createQuery("SELECT a FROM Product a").getResultList();
    }

    public void deleteById(Long id){
        Optional<Product> p = findById(id);
        if(p.isPresent()) {
            em.createQuery("DELETE FROM Product a WHERE a.id = :id").setParameter("id", id);
        }
        else{
            System.out.println("Продукта с таким ID нет в репозитории");
        }
    }

    @Transactional
    public void saveOrUpdate(Product product){
        if(findById(product.getId()).isPresent()) {
            em.getTransaction().begin();
            em.createQuery("update Product set id = :id, name = :name, cost = :cost where id = :id")
                    .setParameter("id", product.getId())
                    .setParameter("name", product.getName())
                    .setParameter("cost", product.getCost())
                    .executeUpdate();
            em.getTransaction().commit();
        }else {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO product (id, name, cost) VALUES (?,?,?)")
                    .setParameter(1, product.getId())
                    .setParameter(2, product.getName())
                    .setParameter(3, product.getCost())
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }
}
