package ru.karinkicks.servlet;

import org.springframework.stereotype.Service;
import ru.karinkicks.entity.Person;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDao {

    private final EntityManagerService entityManagerService;

    public PersonDao(EntityManagerService entityManagerService){
        this.entityManagerService=entityManagerService;
    }

    public Optional<Person> findById(Long id){
        Person p = null;
        try {
            p = (Person) entityManagerService.em.createQuery("SELECT a FROM Person a WHERE a.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException nre){
        }
        return (p==null) ? Optional.empty() : Optional.of(p);
    }

    public List<Person> findAll(){
        return entityManagerService.em.createQuery("SELECT a FROM Person a").getResultList();
    }

    public void deleteById(Long id){
        Optional<Person> p = findById(id);
        if(p.isPresent()) {
            entityManagerService.em.createQuery("DELETE FROM Person a WHERE a.id = :id").setParameter("id", id);
        }
        else{
            System.out.println("Покупателя с таким ID не существует");
        }
    }

    @Transactional
    public void saveOrUpdate(Person person){
        if(findById(person.getId()).isPresent()) {
            entityManagerService.em.getTransaction().begin();
            entityManagerService.em.createQuery("update Person set id = :id, firstName = :firstName, lastName = :lastName where id = :id")
                    .setParameter("id", person.getId())
                    .setParameter("firstName", person.getFirstName())
                    .setParameter("lastName", person.getLastName())
                    .executeUpdate();
            entityManagerService.em.getTransaction().commit();
        }else {
            entityManagerService.em.getTransaction().begin();
            entityManagerService.em.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
                    .setParameter(1, person.getId())
                    .setParameter(2, person.getFirstName())
                    .setParameter(3, person.getLastName())
                    .executeUpdate();
            entityManagerService.em.getTransaction().commit();
        }
    }
}
