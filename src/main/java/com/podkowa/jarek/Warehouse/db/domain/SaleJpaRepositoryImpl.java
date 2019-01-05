package com.podkowa.jarek.Warehouse.db.domain;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Instant;

public class SaleJpaRepositoryImpl implements CustomSaleOperations {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(SaleDto saleDto) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO `sale`\n" +
                        "    (\n" +
                        "     `product_id`,\n" +
                        "     `record_created_on`\n" +
                        "        )\n" +
                        "VALUES (?,?)"
        );

        query
                .setParameter(1, saleDto.getProduct().getId())
                .setParameter(2, Instant.now())
                .executeUpdate();


    }

    @Override
    @Transactional
    public void delete(@Param("id") int id) {
        Query query = entityManager.createNativeQuery("DELETE FROM sale WHERE id = :id");
        query
                .setParameter("id", id)
                .executeUpdate();
    }
}
