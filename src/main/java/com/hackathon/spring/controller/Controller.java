package com.hackathon.spring.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hackathon.spring.model.Model;
import com.hackathon.spring.service.UserService;
@RestController
public class Controller {
	@Autowired
    UserService userService; 
 
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<Model>> listAllUsers() {
        List<Model> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<Model>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Model>>(users, HttpStatus.OK);
    }
 
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Model model = userService.findById(id);
        if (model == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Model>(model, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody Model model,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + model.getUname());
 
        if (userService.isUserExist(model)) {
            System.out.println("A User with name " + model.getUname() + " already exist");
            return new ResponseEntity<String>("New user is not created",HttpStatus.CONFLICT);
        }
 
        userService.saveUser(model);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<String>("New user is created",headers, HttpStatus.CREATED);
    }
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody Model model) {
        System.out.println("Updating User " + id);
         
        Model currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<String>("updated failed!",HttpStatus.NOT_FOUND);
        }
 
        currentUser.setUname(model.getUname());
        currentUser.setAge(model.getAge());
        currentUser.setEmail(model.getEmail());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<String>("updated successfully!",HttpStatus.OK);
    }
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
       
 
        Model model = userService.findById(id);
        System.out.println("Fetching & Deleting User with id " + id+model.getEmail());
        if (model == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<String>("Deleted failed",HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<String>("Deleted succesfully",HttpStatus.OK);
    }
    
    
 

}
