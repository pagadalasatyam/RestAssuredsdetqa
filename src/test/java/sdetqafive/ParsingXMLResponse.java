package sdetqafive;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
    @Test
	void testXMLResponse()
	{
		//Approach1
		/*
		 * given() .when() .get("http://restapi.adequateshop.com/api/Traveler?page=1")
		 * .then() .statusCode(200)
		 * .header("Content-Type","application/xml; charset=utf-8")
		 * .body("TravelerinformationResponse.page",equalTo("1"))
		 * .body("TravelerinformationResponse.travelers.Travelerinformation[0].name",
		 * equalTo("Developer"));
		 */	    //Approach2
    	Response res=
    			given()
    			.when()
                    .get("http://restapi.adequateshop.com/api/Traveler?page=1");
    	
	    Assert.assertEquals(res.getStatusCode(),200);
	    Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
	    String pageNo=res.xmlPath().getString("TravelerinformationResponse.page").toString();
	    Assert.assertEquals(pageNo, "1");
	    String travelername=res.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
	    Assert.assertEquals(travelername,"Developer");
	    
	}   
    @Test
    void testXMLResponseBody()
    {
    	Response res=
    			given()
    			.when()
                    .get("http://restapi.adequateshop.com/api/Traveler?page=1");
    	
    	XmlPath xmlobj=new XmlPath(res.asString());
    	//Verify total number of travelers
    	List<String> travellers= xmlobj.getList("TravelerinforamtionResponse.travelers.Travelerinformation");
        Assert.assertEquals(travellers.size(),10);
        //verify the traveler name(any other attribute) is present in response
        List<String> traveler_names= xmlobj.getList("TravelerinforamtionResponse.travelers.Travelerinformation.name");
        boolean status =false;
        for(String traveler_nm : traveler_names)
        {
        	 System.out.println(traveler_nm);
        	 if(traveler_nm.equals("karen123"))
        	 {
        		 status=true;
        		 break;
        	 }
        }
        Assert.assertEquals(status, true);
    }
}
