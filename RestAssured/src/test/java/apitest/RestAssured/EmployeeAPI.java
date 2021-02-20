package apitest.RestAssured;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.codehaus.groovy.classgen.ReturnAdder;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;

import static io.restassured.RestAssured.*;

public class EmployeeAPI {
	
	//@BeforeMethod
	//@BeforeTest
	public void setUp() {
		
		RestAssured.baseURI="http://dummy.restapiexample.com";
		
		
		
	}
	
	//@Test
	public void Get_Employee() {
		
		
		given()
			.contentType("applicataion/json")
		.when()
			.get("/employees")
		.then()
			.statusCode(200)
			.body("status", Is.is("success"));
				
	}
	
	
	//@Test(priority=0)
	public void get_Employeeid() {
		
		given()
			.contentType("application/json")
		.when()
			.get("/employee/3")
		.then()
			.log().all();
				
	}

	//@Test
	public void put_employee() {
		
		JSONObject add= new JSONObject();
		add.put("name", "vijay");
		add.put("salary", "100");
		add.put("age","25");
			
		
		given()
			.body(add.toJSONString())
		.when()
			.put("/create")
		.then()
			.statusCode(200)
			.log().all();
			
	}
	
	//@Test
	public void postemployee_details() {
		
		JSONObject add1= new JSONObject();
		add1.put("name",RestUtils.getName());
		add1.put("salary", RestUtils.getSalary());
		add1.put("age", RestUtils.getAge());
		
		given()
			.body(add1.toJSONString())
		.when()
			.post("http://dummy.restapiexample.com/create")
		.then()
			.statusCode(201)
			.log().all();
		}
	
	
}
