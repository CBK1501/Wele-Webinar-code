package com.wele.learning.repo;

import com.wele.learning.model.WebinarUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebinarAttendanceRepository extends MongoRepository<WebinarUser, String> {
}
