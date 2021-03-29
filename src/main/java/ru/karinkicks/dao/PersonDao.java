package ru.karinkicks.dao;

import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Person;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    public void init(){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Optional<Person> findById(Long id){
        Person p = null;
        try {
            p = (Person) entityManager.createQuery("SELECT a FROM Person a WHERE a.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException nre){
        }
        return (p==null) ? Optional.empty() : Optional.of(p);
    }

    public List<Person> findAll(){
        return entityManager.createQuery("SELECT a FROM Person a").getResultList();
    }

    public void deleteById(Long id){
        Optional<Person> p = findById(id);
        if(p.isPresent()) {
            entityManager.createQuery("DELETE FROM Person a WHERE a.id = :id").setParameter("id", id);
        }
        else{
            System.out.println("Покупателя с таким ID не существует");
        }
    }

    @Transactional
    public void saveOrUpdate(Person person){
        if(findById(person.getId()).isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.createQuery("update Person set id = :id, firstName = :firstName, lastName = :lastName where id = :id")
                    .setParameter("id", person.getId())
                    .setParameter("firstName", person.getFirstName())
                    .setParameter("lastName", person.getLastName())
                    .executeUpdate();
            entityManager.getTransaction().commit();
        }else {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
                    .setParameter(1, person.getId())
                    .setParameter(2, person.getFirstName())
                    .setParameter(3, person.getLastName())
                    .executeUpdate();
            entityManager.getTransaction().commit();
        }
    }
}
