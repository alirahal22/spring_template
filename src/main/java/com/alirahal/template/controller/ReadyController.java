package com.alirahal.template.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/ready")
public class ReadyController {

    @Value("${ready.test}")
    private String property;


    @GetMapping("")
    public ResponseEntity<Map<String, String>> getReadyStatus() {
        return ResponseEntity.ok().body(Map.of("status", "ready"));
    }

    @GetMapping("/properties")
    public ResponseEntity<Map<String, String>> getProperties() {
        return ResponseEntity.ok().body(Map.of("property", property));
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<Map<String, Object>> postRequest(@RequestBody Map<String, Object> body, @PathVariable
            String id, @RequestParam String name, @RequestParam(required = false) String age) {
        body.put("path", id);
        body.put("query", name);
        body.put("age", age);
        Logger.getAnonymousLogger().info(body.toString());
        return ResponseEntity.ok().body(body);
    }


}
