package com.example.Spring.boot.project1.docs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈 생성자가 생기는 것을 방지합니다.
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String address;
    private String pw;
//    public String getId() {
//        return id;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }
//    public String getAddress() {
//        return address;
//    }
//    public void setAddress(String address) {
//        this.address = address;
//    }
//    public String getPw() {
//        return pw;
//    }
//    public void setPw(String pw) {
//        this.pw = pw;
//    }
}
