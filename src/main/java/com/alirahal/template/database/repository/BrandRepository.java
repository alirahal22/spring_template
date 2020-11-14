package com.alirahal.template.database.repository;

import com.alirahal.template.model.Brand;
import com.alirahal.template.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BrandRepository extends CrudRepository<Brand, UUID> {
}
