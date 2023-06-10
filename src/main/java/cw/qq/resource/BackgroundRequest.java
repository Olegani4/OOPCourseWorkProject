package cw.qq.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BackgroundRequest {
    private String name;  // Name of the background
    private String hex_code;  // Hexadecimal code representing the background color
    private String text_color;  // Color of the text displayed on the background
}
