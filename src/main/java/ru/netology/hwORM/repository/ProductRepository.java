package ru.netology.hwORM.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.hwORM.models.Persons;

import java.util.List;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Persons persons) {
        entityManager.persist(persons);
    }

    public List<Persons> getPersonByCity(String city) {
        TypedQuery<Persons> query = entityManager.createQuery("SELECT p FROM Persons p WHERE p.city_of_living = :city",
                Persons.class);
        query.setParameter("city", city);
        return query.getResultList();
    }
}
