package com.blogapp.payload;

import lombok.Data;

@Data
public class Signup {
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
}
