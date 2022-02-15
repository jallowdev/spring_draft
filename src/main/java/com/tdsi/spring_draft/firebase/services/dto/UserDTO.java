package com.tdsi.spring_draft.firebase.services.dto;

import lombok.Data;


import java.io.Serializable;
import java.util.UUID;

@Data
//@NoArgsConstructor
public class UserDTO implements Serializable {
    private String id=UUID.randomUUID().toString();
    private String userName;

    private String email;
    private String tel;
    private String urlPhoto;
    // à lire
   // private LocalDateTime createdDate = LocalDateTime.now();

}
