package sdetqathree;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;



import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
@Test(priority=1)
void testHeaders()
{
	given()
	.when()
	  .get("https://www.google.com")
	  .then()
	    .header("Content-Type", "text/html; charset=ISO-8859-1")
        .header("Content-Encoding","gzip")
        .header("Server","gws");
}
@Test(priority=2)
void getHeaders()
{
	Response res=given()
			       .when()   
			           .get("https://www.google.com");
	/*
	 * String headervalue=res.getHeader("Content-Type");
	 * System.out.println("The value of content type is:"+headervalue);
	 */
    Headers hd= res.getHeaders();
    for(Header hd1:hd)
    {
    	System.out.println(hd1.getName()+" :  "+hd1.getValue());
    }
    
}
}
