package com.wele.learning.repo;

import com.wele.learning.model.Webinar;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebinarRepository extends MongoRepository<Webinar, String> {

//    @Query("{ 'categories': { $in: ?0 } }")

     @Query("{ 'categories': { $regex: ?0, $options: 'i' } }")
     List<Webinar> findByCategoriesIn(List<String> categories);




}
