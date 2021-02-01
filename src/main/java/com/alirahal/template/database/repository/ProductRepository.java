package com.alirahal.template.database.repository;

import com.alirahal.template.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
