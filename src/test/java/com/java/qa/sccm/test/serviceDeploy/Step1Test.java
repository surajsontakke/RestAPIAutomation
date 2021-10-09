package com.java.qa.sccm.test.serviceDeploy;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.qa.sccm.Headers.APIHeader;
import com.java.qa.sccm.Utility.ExcelUtils;
import com.java.qa.sccm.Utility.XLS_Reader;
import com.java.qa.sccm.base.APIUrl;
import com.java.qa.sccm.base.TestBase;
import com.java.qa.sccm.pojopages.Step1;
import com.java.qa.sccm.test.LoginTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Step1Test extends TestBase {

	/*
	 * { "service_order_id": "7654", "customer_id": "10034567", "sub_service_id":
	 * "1.1.1.1", "endpoint_device_count": 1, "portUD": "no", "adl_type": "false",
	 * "qos": false }
	 */

	TestBase testbase;

	ObjectMapper mapper;
	String Step1Json;
	LoginTest loginTest;
	private final static String testData = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
	private static XLS_Reader xlsx = new XLS_Reader(testData);
	public Hashtable<String, String> data;

	@Test(dataProvider = "Authentication")
	public void ValidationStep1(Map<Object, Object> map) throws JsonProcessingException {
		Step1 step1 = new Step1();
		testbase = new TestBase();
		testbase.setUp();

		Random r = new Random();
		int result = r.nextInt(100000);
		step1.setService_order_id(String.valueOf(result));
		step1.setCustomer_id(String.valueOf(map.get("customerId")));
		step1.setSub_service_id(String.valueOf(map.get("seriverOrderId")));
		step1.setEndpoint_device_count(String.valueOf(map.get("endPointCount")));
		step1.setAdl_type(String.valueOf(map.get("adlType")));
		step1.setPortUD(String.valueOf(map.get("portUD")));
		step1.setQos(String.valueOf(map.get("qos")));

		// mapper = new ObjectMapper();
		// Step1Json =
		// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(step1);
		// System.out.println(Step1Json);

		httpreRequest = RestAssured.given();
		httpreRequest.headers(APIHeader.defaultHeader());

		httpreRequest.body(step1);
		log.info("Step1 json payload : " + httpreRequest.body(step1));

		response = httpreRequest.post(APIUrl.postServiceDeployStep1);
		log.info("Step1 url : " + APIUrl.postServiceDeployStep1);

		// int StatusCode = response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Status code : " + response.getStatusCode());
		log.info("Step1 status code : " + response.getStatusCode());
		String Step1Body = response.body().asString();
		JsonPath js = new JsonPath(Step1Body);
		System.out.println(js.get());
		Assert.assertEquals(js.get("status_code"), 6000);
		Assert.assertEquals(js.get("sub_status_code"), 6001);

	}

	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("Step1", xlsx);
		return testObjArray;

	}

}
