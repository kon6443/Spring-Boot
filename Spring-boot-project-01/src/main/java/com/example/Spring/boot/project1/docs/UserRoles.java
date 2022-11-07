package com.example.Spring.boot.project1.docs;

import lombok.Getter;

@Getter
public enum UserRoles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private final String value;
    UserRoles(String value) {
        this.value = value;
    }
}
