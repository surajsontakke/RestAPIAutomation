package com.java.qa.sccm.test;

import com.java.qa.sccm.Headers.APIHeader;
import com.java.qa.sccm.base.APIUrl;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;

public class GetDevicesByLatlongPincode {

	@Test
	public void validateGetDevicesByLatitudePincode() {

		RestAssured.baseURI = APIUrl.getDevicesLatlongPincode;

		String response = RestAssured.given().headers(APIHeader.defaultHeader())
				.body("{\r\n" + " \"pincode\":\"400701\",\r\n" + " \"device_type\":\"css\"\r\n" + " }").when().log()
				.all().get("find-devices?pincode=" + 400701 + "&device_type=" + "css").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);
		System.out.println(js.get());
	}

}
