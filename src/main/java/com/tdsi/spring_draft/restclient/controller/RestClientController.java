package com.tdsi.spring_draft.restclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdsi.spring_draft.restclient.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(path = "/rest-client")
public class RestClientController {

    @Value("${restclient.message}")
    private String message;

    @Value("${restclient.link.jsonplaceholder}")
    private String jsonPlaceHolderApi;

    private final RestClientService restClientService;

    public RestClientController(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    @GetMapping
    public ResponseEntity  getPhotos() throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        return ResponseEntity.ok(restClientService.findAll());
    }

    @GetMapping("/")
    public ResponseEntity getPhotosById(@RequestParam long id) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        return ResponseEntity.ok(restClientService.findById(id));
    }
}
