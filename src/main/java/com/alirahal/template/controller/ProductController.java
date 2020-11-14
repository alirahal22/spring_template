package com.alirahal.template.controller;

import com.alirahal.template.model.Product;
import com.alirahal.template.services.ProductsService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BasicRestController<Product, ProductsService> {

    /**
     * To add a new operation other than the base CRUD operations
     */
    @PatchMapping(value = {"/{id}/", "/{id}"})
    public ResponseEntity<Product> patch(@PathVariable String id, @RequestBody JsonPatch body) throws Exception {
        Product patchedModel = service.patch(UUID.fromString(id), body);
        return ResponseEntity.ok(patchedModel);
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
