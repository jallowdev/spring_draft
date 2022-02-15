package com.tdsi.spring_draft.main;

import org.springframework.beans.factory.annotation.Value;

public class PropertiesMain {

    @Value("${spring.message}")
    private static String message;

    public static void main(String[] args) {
        System.out.println(message);
    }
}
