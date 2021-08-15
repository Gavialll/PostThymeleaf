package com.example.restapi.Dao.Repository;

import com.example.restapi.Dao.Model.Advertisement;
import com.example.restapi.Dao.Model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Integer> {
    List<Advertisement> findAllByUser(int id);
}
