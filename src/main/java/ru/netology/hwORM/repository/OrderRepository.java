package ru.netology.hwORM.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.hwORM.models.Orders;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Orders orders) {
        if (orders.getId() == null) {
            orders.setId(UUID.randomUUID());
        }
        entityManager.persist(orders);
    }

    public List<Orders> getOrderList(String name) {
        TypedQuery<Orders> query = entityManager.createQuery(
                "SELECT o FROM Orders o JOIN o.customer c WHERE LOWER(c.name) = LOWER(:customerName)",
                Orders.class
        );
        query.setParameter("customerName", name);
        return query.getResultList();
    }

}
