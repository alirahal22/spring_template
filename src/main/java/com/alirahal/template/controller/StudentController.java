package com.alirahal.template.controller;

import com.alirahal.template.model.Student;
import com.alirahal.template.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Student>> getStudent() {
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = {"/{id}/", "/{id}"})
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        Student student = studentService.get(UUID.fromString(id));
        return ResponseEntity.ok(student);
    }

    @PostMapping(value = {"/", ""}, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@RequestBody Student body) {
        studentService.create(body);
        return ResponseEntity.ok(body);
    }

    @PatchMapping(value = {"/{id}/", "/{id}"})
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student body) {
        Student updatedStudent = studentService.update(UUID.fromString(id), body);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping(value = {"/{id}/", "/{id}"})
    public ResponseEntity<Student> deleteStudent(@PathVariable String id) {
        studentService.delete(UUID.fromString(id));
        return ResponseEntity.ok(null);
    }

}
