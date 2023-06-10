package cw.qq.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRequest {
    private String login;  // User's login information
    private String email;  // User's email address
    private String password;  // User's password
    private String image;  // User's profile image URL
}
