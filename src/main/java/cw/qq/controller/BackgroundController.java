package cw.qq.controller;

import cw.qq.models.Background;
import cw.qq.repository.BackgroundRepository;
import cw.qq.resource.BackgroundRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BackgroundController {

    private final BackgroundRepository backgroundRepository;

    public BackgroundController(BackgroundRepository backgroundRepository) {
        this.backgroundRepository = backgroundRepository;
    }

    /**
     * POST endpoint to create a new background.
     *
     * @param backgroundRequest the request body containing the background details
     * @return ResponseEntity with the created background and HTTP status 201 (Created)
     */
    @PostMapping("/backgrounds")
    public ResponseEntity<Background> createBackground(@RequestBody BackgroundRequest backgroundRequest) {
        var background = new Background();  // Create a new Background instance
        background.setName(backgroundRequest.getName());  // Set the name of the background from the request
        background.setHex_code(backgroundRequest.getHex_code());  // Set the hexadecimal code from the request
        background.setText_color(backgroundRequest.getText_color());  // Set the text color from the request

        // Save the background to the repository and return the response with the created background
        return ResponseEntity.status(201).body(this.backgroundRepository.save(background));
    }
}
