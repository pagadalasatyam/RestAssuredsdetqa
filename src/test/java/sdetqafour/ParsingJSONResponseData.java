package sdetqafour;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
public class ParsingJSONResponseData {
    @Test(priority=1)
	void testJsonResponse()
	{
		//Approach1
    	//http://localhost:3000/store
		/*
		 * given() .contentType("ContentType.JSON") .when()
		 * .get("http://localhost:3000/store") .then() .statusCode(200)
		 * .header("Content-Type", "application/json")
		 * .body("book[2].title",equalTo("The Lord of the Rings"));
		 */
    	
    	//Approach2
    	Response res=
    	given()
    	.contentType("ContentType.JSON")
    	.when()
    	 .get("http://localhost:3000/store");
    	Assert.assertEquals(res.getStatusCode(),200);//validation1
        Assert.assertEquals(res.header("content-Type"),"application/json");	
	    String bookname=res.jsonPath().getString("book[2].title").toString();
	    Assert.assertEquals(bookname,"The Lord of the Rings");
	    
	}  
    @Test(priority=2)
	void testJsonResponseBodyData()
	{
    	Response res=
    	given()
    	.contentType(ContentType.JSON)
    	.when()
    	 .get("http://localhost:3000/store");
		/*
		 * Assert.assertEquals(res.getStatusCode(),200);//validation1
		 * Assert.assertEquals(res.header("content-Type"),"application/json"); String
		 * bookname=res.jsonPath().getString("book[2].title").toString();
		 * Assert.assertEquals(bookname,"The Lord of the Rings");
		 */
    	//JSONObject class
    	JSONObject jo= new JSONObject(res.asString());
		/*
		 * for(int i=0;i<jo.getJSONArray("book").length();i++) { String bookTitle=
		 * jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		 * System.out.println(bookTitle); }
		 */
    	boolean status=false;
    	for(int i=0;i<jo.getJSONArray("book").length();i++)
    	{
    	  String bookTitle= jo.getJSONArray("book").getJSONObject(i).get("title").toString();	
    	  System.out.println(bookTitle);
    	  if(bookTitle.equals(""));
    	  {
    		  status=true;
    		  break;
    	  }
    	}
    	Assert.assertEquals(status,true);
	
    	//validate total price of books
        double totalprice=0;
    	for(int i=0;i<jo.getJSONArray("book").length();i++)
    	{
    	  String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
    	  totalprice=totalprice+Double.parseDouble(price) ; 
    	}
        System.out.println("total price of the books:"+ totalprice);
    	Assert.assertEquals(totalprice,451.0);
  }
}
