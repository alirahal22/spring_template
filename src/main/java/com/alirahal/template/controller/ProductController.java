package com.alirahal.template.controller;

import com.alirahal.template.model.Product;
import com.alirahal.template.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BasicRestController<Product, ProductService> {

    /**
     * To add a new operation other than the base CRUD operations
     */
    @GetMapping(value = "/custom_route")
    public ResponseEntity example() {
        return ResponseEntity.accepted().build();
    }

    /**
     * To disable a default CRUD operation
     *
     *  @Override public ResponseEntity<List<Product>> getAll() {
     *      return ResponseEntity.notFound().build();
     *  }
     *
     * or
     *
     *  @Override public ResponseEntity<List<Product>> getAll() throws NotFoundException {
     *      throw new NotFoundException();
     *  }
     *
     */

}
