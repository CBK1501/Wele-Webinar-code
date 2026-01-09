package com.wele.learning.repo;

import com.wele.learning.model.Speaker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends MongoRepository<Speaker, String> {

}
