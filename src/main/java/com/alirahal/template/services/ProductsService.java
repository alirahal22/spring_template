package com.alirahal.template.services;

import com.alirahal.template.config.MapperFactory;
import com.alirahal.template.database.repository.ProductRepository;
import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.io.IOException;
import java.util.UUID;

public class ProductsService extends RestService<Product, ProductRepository> {

}
