package apitest.RestAssured;

import org.hamcrest.core.IsNot;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Reqres {
	
	public static FileInputStream fis;
	public static  Properties prop;
	
	HashMap map= new HashMap();
	
	
	@BeforeTest
	public void propload() throws IOException {
		
		fis= new FileInputStream("C:\\Users\\vijay\\git\\repository\\RestAssured\\resource\\file.properties");
		
		prop= new Properties();
		
		prop.load(fis);
		
		String email= prop.getProperty("email");
		System.out.println("The email value is: "+email);
		
	}
	
	
	//To check delayed response
	@Test
	public void delayedResponse() {
		
		given()
			.queryParam("delay", prop.getProperty("delay"))
			.contentType(ContentType.JSON)
		
		.when()
			.get("https://reqres.in/api/users")
			
		.then()
			.statusCode(200)
			.assertThat().body("total",equalTo(12))
			.assertThat().body("data.id[0]", equalTo(1));
				
	}
	
	//to check unsuccessful login
	@Test()
	public void loginUnsuccessful() {
		
		HashMap<String,Object> tst=new HashMap<String,Object>();
		tst.put("email","peter@klaven");
		
		
		given()
			
		.when()
			.body("{"+
			       "\"email\": \"peter@klaven\""+
					"}")
			
			.post("https://reqres.in/api/login")
		.then()
			.statusCode(400)
			.assertThat().statusLine("HTTP/1.1 400 Bad Request")
			.assertThat().body("error",equalTo("Missing email or username"));
			
	}
	
	@Test
	public void loginSuccessful() {
		
		//HashMap test2 =new HashMap();
		map.put("email",prop.getProperty("email2"));
		map.put("password",prop.getProperty("password"));
				
		System.out.println(prop.getProperty("email2"));
		System.out.println(prop.getProperty("password"));
				
		Response res=given()
			.accept(ContentType.JSON)
			.contentType("application/json")
			.body(map)
		.when()
			.post("https://reqres.in/api/login")
		.then()
			.statusCode(200)
			.extract().response();
		
		String token=res.getBody().jsonPath().get("token");
		System.out.println(token);
		map.clear();
			
		
	}
	
	@Test
	public void regUnsuccessful() {
		
		map.put("email", prop.getProperty("regemail"));
		
		given()
			.contentType(ContentType.JSON)
			.accept("application/json")
		.when()
			.body(map)
			.post("https://reqres.in/api/register")
		.then()
			.statusCode(400)
			.assertThat().body("error", equalTo("Missing password"));
		map.clear();		
		
	}
	
	
	@Test	
	public void regSuccessfull() {
		
		map.put("email",prop.getProperty("regemail"));
		map.put("password",prop.getProperty("regpassword"));
		
		given()
			.contentType(ContentType.JSON)
			.accept("application/json")
		.when()
			.body(map)
			.post("https://reqres.in/api/register")
		.then()
			.statusCode(200)
			.assertThat().body("id",notNullValue());
					
		
		
	}
	
}
