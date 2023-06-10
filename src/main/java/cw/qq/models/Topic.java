package cw.qq.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("topics")
@Data
@RequiredArgsConstructor
public class Topic {
    @Id
    private String id;  // ID of the topic
    private String title;  // Title of the topic
}
