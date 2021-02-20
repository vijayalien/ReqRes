package apitest.RestAssured;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.sql.Time;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl.HamcrestAssertionClosure;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.AnyOf.*;

//import io.restassured.http.Header;
import io.restassured.response.Response;

public class Restapitest {

	Response res;

	@BeforeMethod
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath= baseURI+"/api/";


	}

	//@Test
	public void get() {

		res= RestAssured.get("https://reqres.in/api/users?page=2");

		System.out.println(res.body().asString());
		System.out.println(res.getStatusCode());
		System.out.println(res.getTime());
		System.out.println(res.getContentType());

		res=RestAssured.get("https://reqres.in/api/users/23");

		int code=res.getStatusCode();
		System.out.println(res.getBody().toString());
		AssertJUnit.assertEquals(404, code);

	}

	//@Test
	public void get_singleUser() {

		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
		.get(basePath+"users/2")
		.then()
		.statusCode(200)
		.log().all()
		.body("data.first_name",equalTo("Janet"));

	}

	//@Test(priority=0)
	public void get_singleusernotFound() {


		given()
		.contentType(ContentType.JSON)
		.when()
		.get(basePath+"users/25")
		.then()
		.assertThat().statusCode(equalTo(404));

	}

	//@Test
	public void get_listunknownresource() {



		given()
		.accept(ContentType.JSON)
		.when()
		.get(basePath+"unknown")
		.then()
		.statusCode(200)
		.body("data[2].name",equalTo("true red"))
		.body("data[5].year",Is.is(2005))
		.body("ad.company",IsEqual.equalTo("StatusCode Weekly"));	


	}

	//@Test
	public void get_delay() {


		given()
		.contentType(ContentType.JSON)
		.when()
		.get(basePath+"users?delay=3")
		.then()
		.statusCode(IsNot.not(500));

	}

	//@Test(priority=1)
	public void post() {

		JSONObject req =new JSONObject();
		req.put("name", "morpheus");
		req.put("job", "leader");

		given().
		body(req.toJSONString()).

		when().
		post("https://reqres.in/api/users").

		then().
		log().all();



	}

	//@Test
	public void post_login() {


		JSONObject req2= new JSONObject();
		req2.put("email", "eve.holt@reqres.in");
		req2.put("password","cityslicka");


		given().
		body(req2.toJSONString()).
		header("Content-Type","application/JSON").

		when().
		post(basePath +"api/login").


		then().
		statusCode(200).

		body("token",Is.is("QpwL5tke4Pnpja7X4"));

	}

	//@Test(priority=0)

	public void put() {

		JSONObject req1 =new JSONObject();
		req1.put("name", "morpheus");
		req1.put("job", "leader");

		given().
		body(req1.toJSONString()).
		contentType(ContentType.JSON).
		accept("application/JSON").

		when().
		post(basePath +"api/users/2").

		then().
		body("name",Is.is("morpheus")).and().body("job",Is.is("leader")).
		statusCode(201).
		assertThat().body("name", equalTo("morpheus"));

	}
	
	//@Test
	public void register() {
		JSONObject reg =new JSONObject();
		reg.put("name",RestUtils.getName());
		reg.put("lastname",RestUtils.getLastname());
		reg.put("job",RestUtils.getJob());
		reg.put("email","eve.holt@reqres.in");
		//reg.put("password",RestUtils.getPassword());
		
		given().
		contentType("application/json").
		body(reg.toJSONString()).
		
		
		when().
		post("https://reqres.in/api/register").
		
		then().
		assertThat().statusCode(200).
		and().body(containsString("id"));
		
	}

	@Test
	public void delete() {
		
		given()
		
		.when()
			.delete(basePath+"users/2")
			
		.then()
			.statusCode(anyOf(is(200),is(204)));
			
	}
	
	
	
}
