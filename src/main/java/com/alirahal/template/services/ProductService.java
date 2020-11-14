package com.alirahal.template.services;

import com.alirahal.template.config.MapperFactory;
import com.alirahal.template.database.TimestampsMixIn;
import com.alirahal.template.database.repository.ProductRepository;
import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.UUID;
import java.util.logging.Logger;

public class ProductService extends RestService<Product, ProductRepository> {

    public Product patch(UUID id, JsonPatch patch) throws NotFoundException, JsonPatchException,
            JsonProcessingException {
        Product originalEntity = get(id);
        ObjectMapper mapper = MapperFactory.IGNORE_TIMESTAMPS_MAPPER.provide(Product.class);
        JsonNode patched = patch.apply(mapper.convertValue(originalEntity, JsonNode.class));
        Product newProduct = mapper.treeToValue(patched, Product.class);
        repository.save(newProduct);
        return newProduct;
    }
}
