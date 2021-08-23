package com.example.restapi.Dao.Repository;

import com.example.restapi.Dao.Model.Comment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByAdvertisement(int id);
    @Transactional
    void deleteAllByUserId(int id);
    @Transactional
    void  deleteAllByAdvertisement(int id);
}
