package com.alirahal.template.model;

import com.alirahal.template.database.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
public class Brand extends BaseEntity {
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Product> products;

    public Brand(String name) {
        this.name = name;
    }
}
