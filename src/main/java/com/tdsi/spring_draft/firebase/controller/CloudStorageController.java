package com.tdsi.spring_draft.firebase.controller;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.tdsi.spring_draft.firebase.services.CloudStorageService;
import com.tdsi.spring_draft.firebase.services.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

@RestController
@RequestMapping(path = "/storage")
public class CloudStorageController {

    private CloudStorageService cloudStorageService;
    private final String HOME=System.getProperty("user.home");
    private final String PATH_FILES="src/main/resources/files";
    private final String BUCKET_NAME="users-profiles";
    private Environment environment;


    @Autowired
    public CloudStorageController(CloudStorageService cloudStorageService) {
        this.cloudStorageService = cloudStorageService;
    }

    //@PostMapping(consumes = {"multipart/form-data","application/pdf"})
    @PostMapping(consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity hello(@ModelAttribute ProfileDTO profile ) throws IOException {
        String fileName = profile.getFile().getOriginalFilename();
        //String fileName = profile.getFileName() + UUID.randomUUID().toString() + profile.getExtension();

        /*System.out.println(" PROFILE== "+profile);
        String fileName = profile.getFile().getOriginalFilename();
        Path filepath = Paths.get(PATH_FILES + "/" + fileName);
        profile.getFile().transferTo(filepath);
       */
        upload(profile.getFile());
        return ResponseEntity.ok().body(fileName);
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        //Path p = Paths.get(PATH_FILES + "/" + fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private void uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/spring-boot-fire-firebase-adminsdk.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        //Storage storage=StorageOptions.getDefaultInstance().getService();
        //Storage st=StorageOptions.get
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
    }

    public String upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            File file = this.convertToFile(multipartFile, fileName);
            this.uploadFile(file, fileName);// to convert multipartFile to File// to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return "Successfully Uploaded !";                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return  "Unsuccessfully Uploaded!";
        }

    }


}
