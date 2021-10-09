package com.java.qa.sscm.status;

import static io.restassured.RestAssured.given;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetStatus {
	@Test
	public static void Test() {

		RestAssured.baseURI = "http://173.37.21.131:8080";
		String response = given().log().all()
				.header("Content-type", "application/yang-data+json", "Accept", "application/yang-data+json").auth()
				.preemptive().basic("cisco", "cisco123").when().get("/restconf/data").then().log().all().assertThat()
				.statusCode(200).extract().response().getBody().asString();

		JsonPath js = new JsonPath(response); // for parsing json
		
		Reporter.log(response);
	}
}
