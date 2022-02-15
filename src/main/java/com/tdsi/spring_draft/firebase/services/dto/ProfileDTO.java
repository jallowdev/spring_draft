package com.tdsi.spring_draft.firebase.services.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileDTO {
    //private String fileName;
    //private String extension;
    private MultipartFile file;
}
