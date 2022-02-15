package com.tdsi.spring_draft.firebase.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface CloudStorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    void deleteAll();

    void storeLocale(MultipartFile file);

    Stream<Path> loadAllLocal();

    Path loadLocal(String filename);

    void deleteLocal();
    void deleteAllLocal();
}
