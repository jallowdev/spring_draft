package com.tdsi.spring_draft.propertise.controller;

import com.tdsi.spring_draft.propertise.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "properties")
public class ApplicationPropertiesController {

    @Value("${spring.message}")
    private  String message;

    @Value("${spring.days}")
    private  double days;

    private final DatabaseConfig database;
    private final Environment env;


    public ApplicationPropertiesController(Environment env, DatabaseConfig database) {
        this.env = env;
        this.database = database;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok().body(message);
    }



}
