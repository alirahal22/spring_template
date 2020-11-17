package com.alirahal.template.model;

import com.alirahal.template.database.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@ToString(callSuper = true)
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String name;
    @Column(columnDefinition = "NUMERIC")
    private Float price;
    @Column(columnDefinition = "NUMERIC")
    private Float weight;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    public Product(String name, float price, Brand brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }
}

