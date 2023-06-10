package cw.qq.controller;

import cw.qq.models.Topic;
import cw.qq.repository.TopicRepository;
import cw.qq.resource.TopicRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cw.qq.models.User;
import cw.qq.repository.UserRepository;
import cw.qq.resource.UserRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @PostMapping("/user")
//    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
//        var user = new User();
//        user.setLogin(userRequest.getLogin());
//        user.setEmail(userRequest.getEmail());
//        user.setPassword(userRequest.getPassword());
//        user.setImage(userRequest.getImage());
//        user.setUser_quizzes_id(userRequest.getUser_quizzes_id());
//        user.setCompleted_quizzes_id(userRequest.getCompleted_quizzes_id());
//
//        return ResponseEntity.status(201).body(this.userRepository.save(user));
//    }


//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(this.userRepository.findAll());
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
//        var user = new User();
//        user.setName(userRequest.getName());
//        user.setEmail(userRequest.getEmail());
//        user.setPassword(userRequest.getPassword());
//
//        return ResponseEntity.status(201).body(this.userRepository.save(user));
//    }
//
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity getUserById(@PathVariable String id) {
//
//        Optional<User> user = this.userRepository.findById(id);
//
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        } else {
//            return ResponseEntity.ok("The user with id: " + id + " does not exist");
//        }
//    }
//
//    @DeleteMapping("/user/{id}")
//    public ResponseEntity deleteUserById(@PathVariable String id) {
//
//        Optional<User> user = this.userRepository.findById(id);
//
//        if (user.isPresent()) {
//            this.userRepository.deleteById(id);
//            return ResponseEntity.ok("The user with id: " + id + " has been deleted");
//        } else {
//            return ResponseEntity.ok("The user with id: " + id + " does not exist");
//        }
//    }

}