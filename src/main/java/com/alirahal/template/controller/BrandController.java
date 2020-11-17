package com.alirahal.template.controller;

import com.alirahal.template.model.Brand;
import com.alirahal.template.model.Product;
import com.alirahal.template.services.BrandsService;
import com.alirahal.template.services.ProductsService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/brand")
public class BrandController extends BasicRestController<Brand, BrandsService> {

}
