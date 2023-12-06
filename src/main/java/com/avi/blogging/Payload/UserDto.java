package com.avi.blogging.Payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

public class UserDto {

    private  Long userId;
    @NotEmpty
    @Size(min = 4,message = "name must be grater than 4 charecter!!")
    private  String name;
    @Email(message = "follow standard email pattern")
    private  String email;
    @NotEmpty
//    @Size(min = 3,max = 10,message = "password should be in the range of 3 to 10 charecter!! ")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$",message = "follow the standard password")
    private  String password;
    @NotEmpty
    private String about;



    public Long getUserId() {
        return userId;
    }

    public  void setUserId(Long userId) {
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

    public  void setAbout(String about) {
        this.about = about;
    }
}
