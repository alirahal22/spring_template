package com.alirahal.template.controller;

import com.alirahal.template.model.Student;
import com.alirahal.template.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/student")
public class StudentController extends BasicRestController<Student, StudentService> {

    @GetMapping(value = "/custom_route")
    public ResponseEntity example() {
        Logger.getAnonymousLogger().info("This is a custom route to add other mapping to the generic rest controller");
        return ResponseEntity.accepted().build();
    }
}
