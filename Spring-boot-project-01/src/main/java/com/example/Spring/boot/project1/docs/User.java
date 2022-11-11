package com.example.Spring.boot.project1.docs;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈 생성자가 생기는 것을 방지합니다.
//@Getter
//@Setter
//@AllArgsConstructor

//  DTO
@Data
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String _id;
    private String id;
    private String address;
    private String pw;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
