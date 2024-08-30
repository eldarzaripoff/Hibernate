package ru.netology.hwORM.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.hwORM.models.Customers;

import java.util.UUID;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Customers customers) {
        if (customers.getId() == null) {
            customers.setId(UUID.randomUUID());
        }
        entityManager.persist(customers);
    }
}
