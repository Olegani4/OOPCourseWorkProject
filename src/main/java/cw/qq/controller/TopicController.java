package cw.qq.controller;

import cw.qq.models.Topic;
import cw.qq.repository.TopicRepository;
import cw.qq.resource.TopicRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TopicController {

    private final TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    /**
     * POST endpoint to create a new topic.
     *
     * @param topicRequest the request body containing the topic details
     * @return ResponseEntity with the created topic and HTTP status 201 (Created)
     */
    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody TopicRequest topicRequest) {
        var topic = new Topic();  // Create a new Topic instance
        topic.setTitle(topicRequest.getTitle());  // Set the title of the topic from the request

        // Save the topic to the repository and return the response with the created topic
        return ResponseEntity.status(201).body(this.topicRepository.save(topic));
    }
}
