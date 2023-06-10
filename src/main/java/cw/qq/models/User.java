package cw.qq.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@RequiredArgsConstructor
public class User {
    @Id
    private String id;  // ID of the user
    private String login;  // User's login information
    private String email;  // User's email address
    private String password;  // User's password
    private String image;  // User's profile image URL
}
