package cw.qq.resource;

import cw.qq.models.Question;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class QuizRequest {
    private String id;  // ID of the quiz
    private String topic_title;  // Title of the quiz's topic
    private String name;  // Name of the quiz
    private List<Question> questions;  // List of questions in the quiz
    private int questions_num;  // Number of questions in the quiz
    private int likes;  // Number of likes the quiz has received
    private String author_name;  // Name of the quiz's author
    private String author_email;  // Email of the quiz's author
    private String background_hex;  // Hexadecimal value representing the background color of the quiz
    private String text_color;  // Color of the text in the quiz
    private int time_limit_sec;  // Time limit for the quiz in seconds
}
