package sdetqaone;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
/*
 * given()-contenttype,set cookies,add auth,add param,set headers info ect...
 * when()-get,put,delete
 * then()-validate status code,extract response,extract headers,cookes, response body
 */

import org.testng.annotations.Test;
public class httprequests {
	int id;
	@Test(priority=1)
	void getUsers()//Get method 
	
	{
		given()
		.when()
		  .get("https://reqres.in/api/users?page=2")
		 .then() 
		 .statusCode(200)
		 .body("page",equalTo(2))
		 .log().all();
	}
	@Test(priority=2)
	void createUser()//put method
	{
		HashMap<String, String> data= new HashMap<String, String>();
		data.put("name","venkat");
		data.put("job","Learner");
		
		id=given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");//to update the user , we need Id to update and delete by using id
		/*.then()
		.statusCode(201)
		.log().all();*/
	}
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateUser()//p
	{
		HashMap<String, String> data= new HashMap<String, String>();
		data.put("name","venkat1");
		data.put("job","Learnit");
		
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.put("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}
	@Test(priority=4)
	void deleteUser()
	{
		given()
		.when()
		   .delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204).log().all();
	}
}
