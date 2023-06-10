package cw.qq.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("backgrounds")
@Data
@RequiredArgsConstructor
public class Background {
    @Id
    private String id;  // ID of the background
    private String name;  // Name of the background
    private String hex_code;  // Hexadecimal code representing the background color
    private String text_color;  // Color of the text displayed on the background
}
