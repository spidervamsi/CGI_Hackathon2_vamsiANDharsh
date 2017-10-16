package com.hackathon.spring.service;

import java.util.List;

import com.hackathon.spring.model.Model;
import com.hackathon.spring.repo.Repo;

public interface UserService {
	
	 	Model findById(long id);
     
	    Model findByName(String name);
	     
	    void saveUser(Model model);
	     
	    void updateUser(Model model);
	     
	    void deleteUserById(long id);
	 
	    List<Model> findAllUsers(); 
	     
//	    void deleteAllUsers();
	     
	    public boolean isUserExist(Model model);
//	    public void setRepo(Repo repo);

		void setRepo(Repo repository);
}
