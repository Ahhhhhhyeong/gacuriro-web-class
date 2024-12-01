package com.ishami.project.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ishami.project.model.Post;

public interface PostRepository extends MongoRepository<Post, ObjectId> {

}
