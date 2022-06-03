package full_stack.intro;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

import org.springframework.http.HttpStatus;

public class RestAssured_Greeter_Assertion {
		
	@Test
	public void test1Hello2() {
		String responseString = 
							   given().get("http://localhost:8989/hello2?name=Mohit")
							  .then()
							  .assertThat()
							  .statusCode(HttpStatus.OK.value())
							  .extract()
							  .asString();
		
		Assert.assertEquals(responseString,"Say Hello Mohit"); 
	}
	
	@Test
	public void test2Hello2() {
		String responseString = 
								given()
								.queryParam("name", "Mohit")
								.when()
								.get("http://localhost:8989/hello2")
								  .then()
								  .assertThat()
								  .statusCode(HttpStatus.OK.value())
								  .extract()
								  .asString();
		
		Assert.assertEquals(responseString,"Say Hello Mohit"); 		
	}
	
/*	
	@Test
	public void test3Hello2() {
		String responseString =
					 given()
					.get("http://localhost:8989/hello2?name=Mohit")
				    .then()
//				    .log()
				    .body("data", equalTo("Say Hello Mohit"));
//				    .asString()
//  					.assertThat("Say Hello Mohit");
					 
//		Assert.assertEquals(toString(responseString),"Say Hello Mohit"); 
				  	
	}
*/
	
	@Test
	public void testHello1() {
		String responseString1=
								given()
								//.contentType(ContentType.JSON)
								.pathParam("name", "Mohit")
								.when()
								.get("http://localhost:8989/hello1/{name}")
								.then()
								.statusCode(200)
								.extract()
								.asString();
		
		Assert.assertEquals(responseString1,"Hello Mohit"); 
	}
	
	
	@Test
	public void testGetEmpWithBody() {
	
				given()
				.contentType(ContentType.JSON)	
				.when()
				.get("http://localhost:8989/getemp")
				.then()
				.statusCode(200)	
				.body("eno", equalTo(123))
			    .body("empName", equalTo("Mohit")); 
		
	}
	
	
	@Test
	public void testGetEmp() {
		int eno=
				given()
				.contentType(ContentType.JSON)	
				.when()
				.get("http://localhost:8989/getemp")
				.then()
				.statusCode(200)
				.extract()
				.path("eno");	
			    System.out.println("===============>"+eno);
				Assert.assertEquals(eno,123); 
		
	}
	
	@Test
	public void testGetEmpJsonPath() {
	
		Response res = RestAssured.given().get("http://localhost:8989/getemp");
		Assert.assertEquals(200, res.getStatusCode());
		String json = res.asString(); 
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals(jp.get("eno").toString(), "123");
		
	}

	@Test
	public void testSaveEmp() {
	String response=
					given()
					.contentType(ContentType.JSON)
					.body("{\"eno\":234, \"empName\":\"abc\",\"salary\":4545}")
					.when()
					.post("http://localhost:8989/saveemp")
					.then()
					.extract()
					.asString();
	
		/*
		 * Response responseBody= given() .contentType(ContentType.JSON)
		 * .body("{\"eno\":234, \"empName\":\"abc\",\"salary\":4545}") .when()
		 * .post("http://localhost:8989/saveemp");
		 * 
		 * String bodycontent=responseBody.getBody().asString();
		 */
	Assert.assertEquals(response,"Inserted 234"); 		
	}	
	
	@Test
	public void testRemoveEmp() {
		String responseString = 
				   delete("http://localhost:8989/removeemp?eno=123")
				  .then()
				  .assertThat()
				  .statusCode(HttpStatus.OK.value())
				  .extract()
				  .asString();
		
		Assert.assertEquals(responseString,"Record Deleted: 123"); 
		
	}
	
	
}
