package com.example.restapi.Dao.Repository;

import com.example.restapi.Dao.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}