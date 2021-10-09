package com.java.qa.sscm.status;

import static io.restassured.RestAssured.given;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DeviceSync {
	@Test
	public static void DeviceCheckSync() {

		RestAssured.baseURI = "http://173.37.21.232:8080";
		String response = given().log().all().urlEncodingEnabled(false)
				.header("Content-type", "application/yang-data+json", "Accept", "application/yang-data+json").auth()
				.preemptive().basic("cisco", "cisco123").when()
				.post("/restconf/data/tailf-ncs:devices/device=MAG-2_xe/check-sync").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response); // for parsing json

		System.out.println(js.get());
		Reporter.log(response);
	}
}

