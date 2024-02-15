package sdetqathree;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	//@Test(priority=1)
  void testcookies() {
		
		given()
		.when()
		   .get("https://google.com")
		.then()
		.cookie("AEC","Ae3NU9NevuIkh_sG58cfcoeKEbegv-GzkwyhsY7120uU5EYGS0ETJYH5cA; expires=Fri, 09-Aug-2024 01:37:16 GMT; path=/; domain=.google.com; Secure; HttpOnly; SameSite=lax")
		.log().all();
	}
	@Test(priority=2)
	void getCookesInfo()
	{
		Response res=given()
				       .when()
	    
				       .get("https://www.google.com/");
	    //get single cookies info
		/*
		 * String cookie_value=res.getCookie("AEC");
		 * System.out.println("The value of cookie is==>"+cookie_value);
		 */
		//get all cookie info
		Map<String,String> cookies_values= res.getCookies();
	   // System.out.println(cookies_values.keySet());
		for(String k:cookies_values.keySet())
		{
			String cookie_value=res.getCookie(k);
		    System.out.println(k+" = "+cookie_value);	
		}
	    	
	    	
	}
	
}
