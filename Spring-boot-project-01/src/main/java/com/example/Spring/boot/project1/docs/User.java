package com.example.Spring.boot.project1.docs;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈 생성자가 생기는 것을 방지합니다.
//@Getter
//@Setter
//@AllArgsConstructor

//  DTO
@Data
@Document(collection = "users")
public class User {
    @Id
    private String _id;
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
