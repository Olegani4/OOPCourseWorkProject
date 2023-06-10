package cw.qq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import cw.qq.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
