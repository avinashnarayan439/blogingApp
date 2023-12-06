package com.avi.blogging.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long userId;

    private  String name;

    private  String email;

    private  String password;

    private String about;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> post=new ArrayList<>();



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
