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
import org.springframework.stereotype.Service;

//@Service
//@Configuration
@Component
public class JasyptConfig {
    private static String encryptKey;
    @Value("${jasypt.encryptor.password}")
    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }
    public static String encryptPassword(String pw) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptKey);
        String encryptedPassword = encryptor.encrypt(pw);
        return encryptedPassword;
    }
    public static String decryptPassword(String encryptedPw) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(encryptKey);
        String password = decryptor.decrypt(encryptedPw);
        return password;
    }
}
