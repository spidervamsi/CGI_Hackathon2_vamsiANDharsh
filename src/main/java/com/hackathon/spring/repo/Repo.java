package com.hackathon.spring.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.spring.model.Model;

@Repository
public interface Repo extends MongoRepository<Model,Integer>{

}
