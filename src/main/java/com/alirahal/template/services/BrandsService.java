package com.alirahal.template.services;

import com.alirahal.template.database.repository.BrandRepository;
import com.alirahal.template.model.Brand;

import java.util.List;
import java.util.Set;

public class BrandsService extends RestService<Brand, BrandRepository> {

    @Override
    public List<Brand> getAll() {
        List<Brand> brands = super.getAll();
        brands.forEach(b -> {
                    b.setProducts(null);
                }
        );
        return brands;
    }
}
