package cw.qq.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TopicRequest {
    private String title;  // Title of the topic
}
