package API_Autiomation.SSCM_Automation;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.qa.sccm.base.APIUrl;
import com.java.qa.sccm.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.Reader;

public class Step1 extends TestBase {

	/*
	 * { "service_order_id": "7654", "customer_id": "10034567", "sub_service_id":
	 * "1.1.1.1", "endpoint_device_count": 1, "portUD": "no", "adl_type": "false",
	 * "qos": false }
	 */

	TestBase testbase;
	TestLogin testLogin;
	ObjectMapper mapper;
	String Step1Json;

	@Test
	public void step1Test() throws JsonProcessingException {
		Step1Pojo sp = new Step1Pojo();
		testbase = new TestBase();
		testbase.setUp();
		testLogin = new TestLogin();

		sp.setService_order_id("String");
		sp.setCustomer_id("String");
		sp.setSub_service_id("String");
		sp.setEndpoint_device_count("1");
		sp.setPortUD("no");
		sp.setAdl_type("false");
		sp.setQos("false");

		mapper = new ObjectMapper();
		Step1Json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sp);

		httpreRequest = RestAssured.given();
		httpreRequest.header("Content-Type", "application/json");

		httpreRequest.header("Accept", "application/json");
		httpreRequest.header("Authorization", TestLogin.token);

		httpreRequest.body(Step1Json);

		response = httpreRequest.post(APIUrl.postLoginUrl);
		int StatusCode = response.getStatusCode();
		System.out.println(StatusCode);

		String Step1Body = response.body().asString();
		JsonPath js = new JsonPath(Step1Body);
		System.out.println(js.get());

	}

}
