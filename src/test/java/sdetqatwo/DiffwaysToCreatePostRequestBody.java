package sdetqatwo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


/*
 Different ways to create Post request body
 1.post request body using hashmap
 2.post request  body using  org.json library
 3.By using Pojo class
 4.using external json file
 */
@Test
public class DiffwaysToCreatePostRequestBody {
	String id;
	//Test to create post method body with hashmap
	@Test(priority=1)
	void testpostusingHashmap()
	{
	  HashMap<String, Object> data = new HashMap<String, Object>();
	  data.put("name","Scottabc");
	  data.put("location","usa");
	  data.put("phone", "1234567890");
	  String CourseArr[]= {"C","C++"};
	  data.put("courses", CourseArr);
	  
		/*
		 * given() .contentType("application/json") .body(data) .when()
		 * .post("http://localhost:3000/Students") .then() .statusCode(201)
		 * .body("name",equalTo("Scottabc")) .body("location",equalTo("usa"))
		 * .body("phone",equalTo("1234567890")) .body("courses[0]",equalTo("C"))
		 * .body("courses[1]",equalTo("C++")) .header("Content-Type","application/json")
		 * .log().all();
		 */
	    id=given()
	    		.contentType("application/json")
	    		.body(data)
	    		.when()
	    		.post("http://localhost:3000/Students")
	    		.jsonPath().get("id");
	}
	//Method to generate postmethod body using org.json library
	//@Test(priority=1)
	void testpostusingOrgJsonlib()
	{
	  JSONObject data=new JSONObject();
	  data.put("name","Scottabc");
	  data.put("location","Usa");
	  data.put("Phonenum","1234567890");
	  data.put("name","Scott");
	  String CourseArr[]= {"C","C++"};
	  data.put("courses",CourseArr);
	  
	  given()
	   .contentType("application/json")
	   .body(data.toString())
	  .when()
	   .post("https://localhost:3000/students")
	  .then()
	    .statusCode(201)
	    .body("name",equalTo("Scottabc"))
	    .body("location",equalTo("usa"))
	    .body("phone",equalTo("1234567890"))
	    .body("courses[0]",equalTo("C"))
	    .body("course[1]",equalTo("C++"))
	    .header("Content-Type","application/json; charset=utf-8")
	    .log().all();
	     
	}
	//@Test(priority=1)
	void testpostusingpojo()
	{
	  Pojo_PostRequest data= new Pojo_PostRequest();
	  data.setName("Scottabc");
	  data.setLocation("Usa");
	  data.setPhone("1234567890");
	  String coursesArr[]= {"C","C++"};
	  data.setCourses(coursesArr);
	  given()
	   .contentType("application/json")
	   .body(data)
	  .when()
	   .post("https://localhost:3000/students")
	  .then()
	    .statusCode(201)
	    .body("name",equalTo("Scott"))
	    .body("location",equalTo("usa"))
	    .body("phone",equalTo("1234567890"))
	    .body("courses[0]",equalTo("C"))
	    .body("course[1]",equalTo("C++"))
	    .header("Content-Type","application/json; charset=utf-8")
	    .log().all();
	     
	}
	//@Test(priority=1)
	void testpostusingexternalJsonFile() throws FileNotFoundException
	{
	 File f=new File(".\\body.json");
	 FileReader fr=new FileReader(f);
	 JSONTokener jt= new JSONTokener(fr);
	 JSONObject data = new JSONObject(jt);
	  given()
	   .contentType("application/json")
	   .body(data)
	  .when()
	   .post("https://localhost:3000/students")
	  .then()
	    .statusCode(201)
	    .body("name",equalTo("Scottabc"))
	    .body("location",equalTo("usa"))
	    .body("phone",equalTo("1234567890"))
	    .body("courses[0]",equalTo("C"))
	    .body("course[1]",equalTo("C++"))
	    .header("Content-Type","application/json; charset=utf-8")
	    .log().all();
	     
	}
	
	//deleting the record
	@Test(priority=2)
	void testDelte()
	{
		given()
		.when()
		  .delete("http://localhost:3000/Students/"+id)
	    .then()
	      .statusCode(200);
	    
	}
}
