package com.programcreek.helloworld.repositories;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.programcreek.helloworld.document.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	//Users findAndModify(Query query, Update update,FindAndModifyOptions options, Class<Users> class1);

}
