package full_stack.intro;

import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class RestAssured_Assertion {

	
	@Test
	public void getResponseStatus(){ 
		int statusCode=given()
				         .queryParam("CUSTOMER_ID","68195")
				         .queryParam("PASSWORD","1234!")
				         .queryParam("Account_No","1")
				       .when()
				       .get("http://code.techpanda.org/V4/sinkministatement.php")
				       .getStatusCode(); 
		System.out.println("The response status is "+statusCode); 

		assertEquals(404,statusCode);	
	}	
	
	
	@Test
	public void getAddResponse(){ 
		
		int statusCode=given()
				         .queryParam("num1","10")
				         .queryParam("num2","20")				       
				       .when()
				       .get("http://localhost:8989/add")
				       .getStatusCode(); 
		System.out.println("The response status is "+statusCode); 
		assertEquals(200,statusCode);
	}
		
	@Test
	public void getAddResponse2() {
	  given()
        .queryParam("num1","10")
        .queryParam("num2","20")				       
      .when()
      .get("http://localhost:8989/add")
      .then()
        .assertThat()
        .statusCode(200); 
    
	}
}
