package com.tdsi.spring_draft.restclient.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdsi.spring_draft.restclient.dao.PhotoRepository;
import com.tdsi.spring_draft.restclient.services.dto.PhotoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
public class RestClientServiceImp implements RestClientService{

    private final PhotoRepository repository;

    public RestClientServiceImp(PhotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PhotoDTO> findAll() throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        return repository.findAll();
    }

    @Override
    public Optional<PhotoDTO> findById(long id) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        return repository.findById(id);
    }
}
