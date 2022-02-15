package com.tdsi.spring_draft.firebase.controller;

import com.tdsi.spring_draft.firebase.services.FirestoreService;
import com.tdsi.spring_draft.firebase.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/firestore")
public class FirebaseController {


    private FirestoreService firestoreService;

    @Autowired
    public FirebaseController(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @GetMapping(path = "/hello")
    public ResponseEntity hello() {
        Map response = new HashMap();
        response.put("message", "Hello World!");
        response.put("Status", 200);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    private ResponseEntity createUser(@RequestBody UserDTO dto) {
        firestoreService.saveUser(dto);
        return ResponseEntity.ok("User Created Success !!!");
    }

    @GetMapping
    private ResponseEntity getUsers() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(firestoreService.getUsers());
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity getUserByID(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(firestoreService.findById(id));
    }

    @DeleteMapping
    private ResponseEntity deleteUser(@PathParam(value = "id") String id) {
        firestoreService.remove(id);
        return ResponseEntity.ok("Remove Created Success !!!");
    }


}
