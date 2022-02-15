package com.tdsi.spring_draft.firebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitialize {

   @PostConstruct
    public void firebaseInit() throws IOException {
        InputStream serviceAccount = new FileInputStream("src/main/resources/spring-boot-fire-firebase-adminsdk.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

       StorageOptions.newBuilder().setCredentials(credentials).build().getService();

   }

}
