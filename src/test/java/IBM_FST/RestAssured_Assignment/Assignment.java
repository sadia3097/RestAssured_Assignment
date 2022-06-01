package IBM_FST.RestAssured_Assignment;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.util.IOUtils;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Assignment {
	public static Response res = null;

	@Test(enabled = true, description = "perform the post operation using escape character")
	public void testcase1() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String body = "{\"username\":\"Geet\",\"firstName\":\"Geethika\",\"lastName\":\"Preet\",\"email\":\"xyz@abc.com\",\"password\":\"passwordfindme\",\"phone\":\"9087654321\"}";
		res = given().header("content-type", "application/json")
				// contentType(ContentType.JSON)
				.body(body).when().post("/user").then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(enabled = true, description = "perform the get operation")
	public void testcase2() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

		res = given().header("content-type", "application/json").pathParam("username", "Geet").when()
				.get("/user/{username}").then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(enabled = true, description = "perform the post operation using jsonfile")
	public void testcase3() throws IOException {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		FileInputStream jsonfile = new FileInputStream(new File(".\\data\\jsonfile.json"));
		res = given().body(jsonfile).header("Content-Type", "application/json").when().post("/user")
				.then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(enabled = true, description = "perform the put operation using escape character")
	public void testcase4() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String body = "{\"username\":\"Geet\",\"firstName\":\"Geethika\",\"lastName\":\"Preet\",\"email\":\"xyz@abc.com\",\"password\":\"passwordfindme\",\"phone\":\"9087654321\"}";
		res = given().header("content-type", "application/json").pathParam("username", "Priya").body(body).when()
				.put("/user/{username}").then().statusCode(200).log().all().extract().response();
	}

	@Test(enabled = true, description = "perform the put operation using jsonfile character")
	public void testcase5() throws FileNotFoundException {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		FileInputStream jsonfile = new FileInputStream(new File(".\\data\\jsonfile.json"));
		res = given().header("content-type", "application/json").pathParam("username", "Priya").body(jsonfile).when()
				.put("/user/{username}").then().statusCode(200).log().all().extract().response();
	}

	@Test(enabled = true, description = "perform the delete operation")
	public void testcase6() throws FileNotFoundException {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

		res = given().header("content-type", "application/json").pathParam("username", "Geet").when()
				.delete("/user/{username}").then().statusCode(200).log().all().extract().response();
	}

	@Test(enabled = true, description = "verify the 404 error code")
	public void testcase7() throws FileNotFoundException {
		RestAssured.baseURI = "https://petstore.swagger.io/v3";

		res = given().header("content-type", "application/json").pathParam("username", "sai").when()
				.get("/user/{username}").then().statusCode(404).log().all().extract().response();
	}

}
