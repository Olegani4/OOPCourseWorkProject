package cw.qq.repository;

import cw.qq.models.Background;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BackgroundRepository extends MongoRepository<Background, String> {
}
