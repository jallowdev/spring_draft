package com.tdsi.spring_draft.restclient.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdsi.spring_draft.restclient.services.dto.PhotoDTO;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface RestClientService {
    List<PhotoDTO> findAll() throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException;
    Optional<PhotoDTO> findById(long id) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException;
}
