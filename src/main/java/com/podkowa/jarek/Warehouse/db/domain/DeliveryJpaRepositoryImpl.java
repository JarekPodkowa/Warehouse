package com.podkowa.jarek.Warehouse.db.domain;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Instant;

public class DeliveryJpaRepositoryImpl implements CustomDeliveryOperations {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(DeliveryDto deliveryDto) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO `delivery`\n" +
                        "    (\n" +
                        "     `product_id`,\n" +
                        "     `record_created_on`\n" +
                        "        )\n" +
                        "VALUES (?,?)"
        );

        query
                .setParameter(1, deliveryDto.getProduct().getId())
                .setParameter(2, Instant.now())
                .executeUpdate();

    }

    @Override
    @Transactional
    public void delete(@Param("id") int id) {
        Query query = entityManager.createNativeQuery("DELETE FROM delivery WHERE id = :id");
        query
                .setParameter("id", id)
                .executeUpdate();
    }
}
