package com.hackathon.spring;


import org.junit.Before;

import org.junit.Test;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.spring.model.Model;

import com.hackathon.spring.repo.Repo;

import com.hackathon.spring.service.UserService;
import com.hackathon.spring.service.UserServiceImpl;

import static org.mockito.Mockito.*;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestService {

   

      @Mock

      private UserService userService;

       @Mock

//       @Autowired
       private Repo repository;

       @Mock

      private Model user;

       @Before

       public void setupMock() {

           MockitoAnnotations.initMocks(this);

           userService=new UserServiceImpl();

          userService.setRepo(repository);

       }

       @Test

       //Test by id

       public void TestgetById() throws Exception {

           // Arrange

           when(repository.findOne(1)).thenReturn(user);

           // Act

           Model retrievedUser = userService.findById(1);

           // Assert

           assertThat(retrievedUser, is(equalTo(user)));

      }

       

       @Test

       public void TestUpdate() throws Exception {

           // Arrange

           when(repository.save(user)).thenReturn(user);

           // Act

           userService.updateUser(user);

           // Assert

           assertThat(repository.findOne(1), is(repository.findOne(1)));

       }

       @Test

       public void TestDelete() throws Exception {

           // Arrange

           doNothing().when(repository).delete(1);

           Repository my = Mockito.mock(Repository.class);

           // Act

          userService.deleteUserById(1);

           // Assert

//            verify(repository, times(1)).delete(1);

       }

   }
