package apitest.RestAssured;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestUtils {

	
	

	public static String getName() {
		String getgeneratedname=RandomStringUtils.randomAlphabetic(4);
		return (getgeneratedname);

	}
	
	public static String getLastname() {
		String getgeneratedlastname=RandomStringUtils.randomAlphabetic(5);
		return getgeneratedlastname;
				
	}
	
	public static String getJob() {
		String getgeneratedjob=RandomStringUtils.randomAlphabetic(8);
		return getgeneratedjob;
		
	}
	
	public static String getEmail() {
		String getgeneraedemail= (RandomStringUtils.randomAlphabetic(6)+"@gmail.com");
		return getgeneraedemail;
				
	}
	
	public static int getId() {
		int getgeneratedid=RandomUtils.nextInt(0, 3);
		return getgeneratedid;
	}
	
	public static int getSalary() {
		int getgeneratedSal=RandomUtils.nextInt(0, 5);
		return getgeneratedSal;
	}
	
	public static int getAge() {
		int getgeneratedAge=RandomUtils.nextInt(0, 2);
		return getgeneratedAge;
	}
	
}



