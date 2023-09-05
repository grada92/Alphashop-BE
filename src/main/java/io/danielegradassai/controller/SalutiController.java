package io.danielegradassai.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SalutiController {

    @GetMapping(value = "/saluti")
    public String getSaluti() {
        return "\"Saluti , Spring Boot!\"";
    }
}
