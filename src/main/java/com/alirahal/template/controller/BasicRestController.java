package com.alirahal.template.controller;

import com.alirahal.template.database.BaseEntity;
import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.services.RestService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BasicRestController<Model extends BaseEntity, Service extends RestService<Model, ?>> {

    /**
     * @noinspection SpringJavaInjectionPointsAutowiringInspection
     */
    @Autowired
    Service service;

    @GetMapping(value = {""})
    public ResponseEntity<List<Model>> getAll(@RequestParam(required = false, name = "offset") Integer page,
                                              @RequestParam(required = false, name = "limit") Integer size,
                                              @RequestParam(required = false, name = "sort") List<String>
                                                      sortingParams) {
        if (size != null && page != null)
            return ResponseEntity.ok(service.getByPage(page, size, sortingParams));
        if (sortingParams != null && !sortingParams.isEmpty())
            return ResponseEntity.ok(service.getSorted(sortingParams));
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Model> getById(@PathVariable String id) throws NotFoundException {
        Model item = service.get(UUID.fromString(id));
        return ResponseEntity.ok(item);
    }

    @PostMapping(value = {"/"}, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> add(@RequestBody Model body) {
        service.create(body);
        return ResponseEntity.ok(body);
    }

    @PatchMapping(value = {"/{id}"})
    public ResponseEntity<Model> patch(@PathVariable String id, @RequestBody JsonNode body) throws NotFoundException,
            IOException {
        Model updatedModel = service.update(UUID.fromString(id), body);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Model> delete(@PathVariable String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.ok(null);
    }
}
