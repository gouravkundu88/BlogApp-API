package com.blogapp.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class PostDto {
    private long id;

    @NotEmpty
    @Size(min = 3, message = "Title should be atleast 3 character")
    private String title;

    @NotEmpty
    @Size(min = 3, message = "Description should be atleast 3 character")
    private String description;

    @NotEmpty
    @Size(min = 3, message = "Description should be atleast 3 character")
    private String content;

}
