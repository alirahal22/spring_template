package com.alirahal.template.controller;

import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.error.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ready")
public class ReadyController {

    @Value("${ready.test}")
    private String property;


    @GetMapping("")
    public ResponseEntity<Map<String, String>> getReadyStatus() throws Exception {
        return ResponseEntity.ok().body(Map.of("status", "ready"));
    }

    @GetMapping("/properties")
    public ResponseEntity<Map<String, String>> getProperties() {
        return ResponseEntity.ok().body(Map.of("property", property));
    }

    /**
     * @RequestParam Map<String   ,       String> allParams
     * can be used to map all params instead of passing
     * each one as a parameter
     **/
    @PostMapping("/post/{id}")
    public ResponseEntity<Map<String, Object>> postRequest(@RequestBody Map<String, Object> body, @PathVariable
            String id, @RequestParam String name, @RequestParam(required = false) String age) {
        body.put("path", id);
        body.put("query", name);
        body.put("age", age);
        return ResponseEntity.ok().body(body);
    }


}
