package API_Autiomation.SSCM_Automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.Reporter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import com.java.qa.sccm.base.TestBase;

/**
 * Hello world!
 *
 */
public class scrach extends TestBase {

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

	@Test(priority = 2)
	public static void DeviceCheckSync() {

	//	http://173.37.21.232:8080/restconf/data/tailf-ncs:devices/device=MAG-2_xe/check-sync
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
