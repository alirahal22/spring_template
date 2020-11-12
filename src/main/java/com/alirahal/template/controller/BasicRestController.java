package com.alirahal.template.controller;

import com.alirahal.template.database.BaseEntity;
import com.alirahal.template.model.Student;
import com.alirahal.template.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BasicRestController<Model extends BaseEntity, Service extends RestService<Model, ?>> {

    /** @noinspection SpringJavaInjectionPointsAutowiringInspection*/
    @Autowired
    Service service;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Model>> getStudent() {
        List<Model> students = service.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = {"/{id}/", "/{id}"})
    public ResponseEntity<Model> getModel(@PathVariable String id) {
        Model student = service.get(UUID.fromString(id));
        return ResponseEntity.ok(student);
    }

    @PostMapping(value = {"/", ""}, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> addModel(@RequestBody Model body) {
        service.create(body);
        return ResponseEntity.ok(body);
    }

    @PatchMapping(value = {"/{id}/", "/{id}"})
    public ResponseEntity<Model> updateModel(@PathVariable String id, @RequestBody Model body) {
        Model updatedModel = service.update(UUID.fromString(id), body);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping(value = {"/{id}/", "/{id}"})
    public ResponseEntity<Model> deleteModel(@PathVariable String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.ok(null);
    }
}
