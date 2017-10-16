package com.hackathon.spring;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackathon.spring.model.Model;
//import com.user.spring.domain.UserModel;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = HackathonApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HackathonApplicationTests {

	  String user1;

	    @LocalServerPort
	    private int port;

	    TestRestTemplate restTemplate = new TestRestTemplate();

	    HttpHeaders headers = new HttpHeaders();

	    public Model userModel;

	    @Before
	    public void setUp() throws Exception {
	         userModel = new Model(1,"abc",13,"aa");
	    }
	    private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }

	    @After
	    public void tearDown() throws Exception {
	    }

	    @Test
	    public void TestCreate() throws Exception {

	        HttpEntity<Model> entity = new HttpEntity<Model>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/user/"),
	                HttpMethod.POST, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println(actual);
	        assertEquals("New user is created",actual);
	    }
	    

	 
	    @Test
	    public void TestUpdate() throws Exception {

	        HttpEntity<Model> entity = new HttpEntity<Model>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/user/2"),
	                HttpMethod.PUT, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println(actual);
	        assertEquals("updated successfully!",actual);
	    }
 
	    @Test
	    public void TestDelete() throws Exception {

	        HttpEntity<Model> entity = new HttpEntity<Model>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/user/1"),
	                HttpMethod.DELETE, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println("DELETE"+actual);
	        assertEquals("Deleted succesfully",actual);
	    }


	    @Test
	    public void TestRead() throws Exception {

	        HttpEntity<Model> entity = new HttpEntity<Model>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/user/"),
	                HttpMethod.GET, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println(actual);
	        

	        assertEquals(true,actual.contains("id") && actual.contains("name") && 
	        		actual.contains("age") && actual.contains("email"));
	    }
	
}