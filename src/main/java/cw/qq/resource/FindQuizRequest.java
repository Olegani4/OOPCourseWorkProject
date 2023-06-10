package cw.qq.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FindQuizRequest {
    private String quiz_name;  // Name of the quiz to search for
    private String author_name;  // Name of the quiz's author to filter by
}
