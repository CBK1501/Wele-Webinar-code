package com.wele.learning.repo;

import com.wele.learning.model.WebinarUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebinarUserRepository extends MongoRepository<WebinarUser, String> {

    WebinarUser findFirstByEmailAndWebinarId(String email, String webinarId);

    @Query(value = "{ 'webinarId': ?0, 'registered': true }", count = true)
    long countByWebinarIdAndRegisteredTrue(String webinarId);

    @Query("{ 'isActive': true }")
    List<WebinarUser> findActiveWebinarUsers();

}
