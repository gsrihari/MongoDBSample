package com.programcreek.helloworld.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.programcreek.helloworld.document.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

}
