package com.alirahal.template.model;

import com.alirahal.template.database.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Student extends BaseEntity {

    private String name;
    private int average;

}