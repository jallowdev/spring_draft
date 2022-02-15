package com.tdsi.spring_draft.firebase.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class CloudStorageServiceImp implements CloudStorageService {

    private static String HOME = System.getProperty("user.home");

    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void storeLocale(MultipartFile file) {

    }

    @Override
    public Stream<Path> loadAllLocal() {
        return null;
    }

    @Override
    public Path loadLocal(String filename) {
        return null;
    }

    @Override
    public void deleteLocal() {

    }

    @Override
    public void deleteAllLocal() {

    }
}
