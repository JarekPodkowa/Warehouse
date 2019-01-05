package com.podkowa.jarek.Warehouse.db.domain;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Instant;

public class ProductJpaRepositoryImpl implements CustomProductOperations {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void insertIntoProduct(ProductDto productDto) {
        Query query = entityManager.createNativeQuery(
                "INSERT into `product`\n" +
                        "    (\n" +
                        "     `code`,\n" +
                        "     `name`,\n" +
                        "     `net_price`,\n" +
                        "     `gross_price`,\n" +
                        "     `quantity`,\n" +
                        "     `record_created_on`\n" +
                        "        )\n" +
                        "VALUES (?,?,?,?,?,?)"
        );

        query
                .setParameter(1, productDto.getCode())
                .setParameter(2, productDto.getName())
                .setParameter(3, productDto.getNetPrice())
                .setParameter(4, productDto.getGrossPrice())
                .setParameter(5, productDto.getQuantity())
                .setParameter(6, Instant.now())
                .executeUpdate();

    }

    @Override
    @Transactional
    public void deleteFromProduct(@Param("id") int id) {
        Query query = entityManager.createNativeQuery( "DELETE FROM product WHERE id = :id");
        query
                .setParameter("id", id)
                .executeUpdate();

    }
}
