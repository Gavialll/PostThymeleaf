package com.example.restapi.Dao.Repository;

import com.example.restapi.Dao.Model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByAdvertisement(int id);
}
