package cw.qq.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Question {
    @Id
    private String id;  // ID of the question
    private String type;  // Type of the question (e.g., multiple choice, true/false)
    private String content;  // Content or text of the question
    private List<String> choice_of_answers;  // List of answer choices (if applicable)
    private String answer;  // Correct answer to the question
}
