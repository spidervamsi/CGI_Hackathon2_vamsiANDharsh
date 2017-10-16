package com.hackathon.spring.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.hackathon.spring.model.Model;
import com.hackathon.spring.repo.Repo;

@Service("userService")

public class UserServiceImpl implements UserService{
	public Repo getRepo() {
		return repo;
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}


	private static AtomicLong counter = new AtomicLong();
	
	@Autowired
	 Repo repo;

	private static List<Model> model;
//	static {
//		model=repo.findAll();
//	}
	@Override
	public Model findById(long id) {
		  if(repo.findOne((int) id)==null)
		        return null;
		    else
		        return repo.findOne((int) id);  
	}

	@Override
	public Model findByName(String name) {
		 for(Model model : repo.findAll()){
		       if(model.getUname().equalsIgnoreCase(name)){
		           return model;
		       }
		   }
		   return null;
	}

	@Override
	public void saveUser(Model model1) {
		model1.setId(counter.incrementAndGet());
        repo.save(model1);
		
	}

	@Override
	public void updateUser(Model model1) {
		repo.save(model1);
		
	}

	@Override
	public void deleteUserById(long id) {
		if(repo.findOne((int) id)!=null)
            repo.delete((int) id);
	}

	@Override
	public List<Model> findAllUsers() {
		List<Model> list=new ArrayList<>();
		list.addAll((Collection<? extends Model>) repo.findAll());
		return list;
	}


	@Override
	public boolean isUserExist(Model model1) {
		
		return findByName(model1.getUname())!=null;
	}

}
