package com.alirahal.template.model;

import com.alirahal.template.database.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@Entity
public class Product extends BaseEntity {

    private String name;
    @Column(columnDefinition = "NUMERIC")
    private float price;
    @Column(columnDefinition = "NUMERIC")
    private float weight;

//    private Brand brand;

}