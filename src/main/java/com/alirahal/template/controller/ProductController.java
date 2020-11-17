package com.alirahal.template.controller;

import com.alirahal.template.model.Product;
import com.alirahal.template.services.ProductsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BasicRestController<Product, ProductsService> {

}
