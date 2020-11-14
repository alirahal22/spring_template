package com.alirahal.template.controller;

import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.model.Student;
import com.alirahal.template.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/student")
public class StudentController extends BasicRestController<Student, StudentService> {

    /**
     * To add a new operation other than the base CRUD operations
     */
    @GetMapping(value = "/custom_route")
    public ResponseEntity example() {
        Logger.getAnonymousLogger().info("This is a custom route to add other mapping to the generic rest controller");
        return ResponseEntity.accepted().build();
    }

    /**
     * To disable a default CRUD operation
     *
     *  @Override public ResponseEntity<List<Student>> getAll() {
     *      return ResponseEntity.notFound().build();
     *  }
     *
     * or
     *
     *  @Override public ResponseEntity<List<Student>> getAll() throws NotFoundException {
     *      throw new NotFoundException();
     *  }
     *
     */

}
