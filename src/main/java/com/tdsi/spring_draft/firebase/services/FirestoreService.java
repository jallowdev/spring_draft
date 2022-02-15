package com.tdsi.spring_draft.firebase.services;

import com.tdsi.spring_draft.firebase.services.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public interface FirestoreService {

    void saveUser(UserDTO dto);

    Optional<UserDTO> findById(String id) throws ExecutionException, InterruptedException;

    List<UserDTO> getUsers() throws ExecutionException, InterruptedException;

    void remove(String id);


}
