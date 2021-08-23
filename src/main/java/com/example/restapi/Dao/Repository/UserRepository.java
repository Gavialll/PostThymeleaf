package com.example.restapi.Dao.Repository;

import com.example.restapi.Dao.Model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    @Transactional
    void deleteById(int id);
}
