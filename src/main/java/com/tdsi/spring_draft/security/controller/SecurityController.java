package com.tdsi.spring_draft.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(path = "security")
public class SecurityController {

    @GetMapping
    @RolesAllowed("USER")
    public ResponseEntity hello() {
        return ResponseEntity.ok().body("Hello Security With Spring Boot !!!! ");
    }

    @RolesAllowed("USER")
    @RequestMapping("/*")
    public String getUser() {
        return "Welcome User";
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/admin")
    public String getAdmin() {
        return "Welcome Admin";
    }


}
