package com.alirahal.template.model;

import com.alirahal.template.database.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@Entity
public class Brand extends BaseEntity {
    private String name;
}
