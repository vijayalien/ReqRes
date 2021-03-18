package apitest.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
public class Resource {
	
	@BeforeClass
	@BeforeTest
	public static RequestSpecification basegiven() {
		
		RestAssured.baseURI="https://reqres.in/api";
		
		return given()
				.accept(ContentType.JSON)
				.contentType("application/json");
	}
}
