package com.example.Spring.boot.project1.controllers;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class JasyptConfig {
    @Value("${jasypt.encryptor.password}")
    private String encryptKey;
    public void encryptPassword(String pw) {
        String temp = "jasyptStringEncryptor";
        System.out.println("Original password: " + pw);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        System.out.println("encryptKey: " + encryptKey);
        encryptor.setPassword(temp);
        String encryptedPassword = encryptor.encrypt(pw);
        System.out.println("Encrypted password: " + encryptedPassword);
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(temp);
        System.out.println(decryptor.decrypt(encryptedPassword));
    }
}
