package sdetqathree;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LoggingDemo {
 @Test
	void testLogs()
 {
	 given()
	  .when()
	    .get("https://www.google.com")
	  .then()
	   //.log().all();
	  .log().headers();
	  //.log().cookies();
	  //.log().body();
	 }
}
