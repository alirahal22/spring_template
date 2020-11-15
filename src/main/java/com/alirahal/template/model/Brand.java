package com.alirahal.template.model;

import com.alirahal.template.database.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@ToString(callSuper = true)
@Entity
public class Brand extends BaseEntity {
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
