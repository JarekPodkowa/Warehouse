package com.podkowa.jarek.Warehouse.db.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column
    private String name;

    @Column(name = "net_price")
    private BigDecimal netPrice;

    @Column(name = "gross_price")
    private BigDecimal grossPrice;
    
    @Column
    private int quantity;

    @CreationTimestamp
    @Column(name = "record_created_on")
    private Instant recordCreatedOn;

}