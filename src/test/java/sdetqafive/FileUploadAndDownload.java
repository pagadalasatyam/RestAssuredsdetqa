package sdetqafive;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {
    @Test
	void singleFileUpload()
	{
    	 File myfile = new File("C:\\Users\\pagad\\eclipse-workspace\\RestAssuredsdetqa\\body.json");
		 given()
		 .multiPart("file",myfile)
		 .contentType("multipart/formdata")
		    .when()
		    // this url is not public , we may need to find the pulblic one and use that URL		    
		     .post("http://localhost:8080/uploadfile")
		  .then()
		    .statusCode(200)
		    .body("filename", equalTo("Text1.txt"))
		    .log().all();
	}
    @Test
   	void multipleFileUpload()
   	{
       	 File myfile1= new File("C:\\Users\\pagad\\eclipse-workspace\\RestAssuredsdetqa\\body.json");
       	 File myfile2= new File("C:\\Users\\pagad\\eclipse-workspace\\RestAssuredsdetqa\\store.json");
   		 given()
   		 .multiPart("files",myfile1)
   		 .multiPart("files",myfile2)
   		 .contentType("multipart/formdata")
   		    .when()
   		     // this url is not public , we may need to find the pulblic one and use that URL
   		     .post("http://localhost:8080/uploadmultiplefile")
   		  .then()
   		    .statusCode(200)
   		    .body("[0].filename", equalTo("body.json"))
            .body("[1].filename", equalTo("Store.json"))
   		    .log().all();
   	}
    @Test
   	void multipleFileUpload2() //may not work for all the API's
   	{
       	 File myfile1= new File("C:\\Users\\pagad\\eclipse-workspace\\RestAssuredsdetqa\\body.json");
       	 File myfile2= new File("C:\\Users\\pagad\\eclipse-workspace\\RestAssuredsdetqa\\store.json");
   		 File fileArr[] = {myfile1,myfile2}; 
       	 given()
   		 .multiPart("files",fileArr)
   		 .contentType("multipart/formdata")
   		    .when()
   		     // this url is not public , we may need to find the pulblic one and use that URL
   		     .post("http://localhost:8080/uploadmultiplefile")
   		  .then()
   		    .statusCode(200)
   		    .body("[0].filename", equalTo("body.json"))
            .body("[1].filename", equalTo("Store.json"))
   		    .log().all();
   	}
  @Test
  void fileDownLoad()
  {
	  given()
	    .when()
	       .get("http://localhost:8080/downloadfile/body.json")
	    .then()
	       .statusCode(200)
	       .log().body();
  }
}
