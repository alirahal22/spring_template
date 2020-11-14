package com.alirahal.template.model;

import com.alirahal.template.database.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends BaseEntity {

    private String name;
    @Column(columnDefinition = "NUMERIC")
    private float price;
    @Column(columnDefinition = "NUMERIC")
    private float weight;

//    private Brand brand;

}

