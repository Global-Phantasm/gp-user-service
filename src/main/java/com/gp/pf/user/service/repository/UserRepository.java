package com.gp.pf.user.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gp.pf.user.service.model.User;

public interface UserRepository extends MongoRepository<User, String>{

}
