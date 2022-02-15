package com.tdsi.spring_draft.firebase.services;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.tdsi.spring_draft.firebase.services.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class FirestoreServiceImpl implements FirestoreService {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private final String COLLECTION_NAME = "users";
    private CollectionReference collectionRef = dbFirestore.collection(COLLECTION_NAME);

    public void saveUser(UserDTO dto) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", dto.getId());
        data.put("userName", dto.getUserName());
        data.put("email", dto.getEmail());
        data.put("tel", dto.getTel());
        data.put("url", dto.getUrlPhoto());
        //data.put("createdDate", dto.getCreatedDate());
        collectionRef.document(dto.getId()).set(data);
    }

    public Optional<UserDTO> findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = collectionRef.document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        UserDTO user = null;
        if (document.exists()) {
            user = document.toObject(UserDTO.class);
        } else {
            System.out.println("No such document!");
        }
        return Optional.ofNullable(user);
    }

    public List<UserDTO> getUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = dbFirestore.collection("users").get();
        List<QueryDocumentSnapshot> list = query.get().getDocuments();

        List<UserDTO> users = new ArrayList<>();
        if (!list.isEmpty()) {
            users = list.stream().map(user -> user.toObject(UserDTO.class)).collect(Collectors.toList());
        }
        return users;
    }

    public void remove(String id) {
        collectionRef.document(id).delete();
    }
}
