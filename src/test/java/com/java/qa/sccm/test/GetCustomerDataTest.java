package com.java.qa.sccm.test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.qa.sccm.Headers.APIHeader;
import com.java.qa.sccm.Utility.ExcelUtils;
import com.java.qa.sccm.Utility.XLS_Reader;
import com.java.qa.sccm.base.APIUrl;
import com.java.qa.sccm.base.TestBase;
import com.java.qa.sccm.pojopages.GetCustomerData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetCustomerDataTest extends TestBase {

	public GetCustomerDataTest() {
		super();
	}

	static ObjectMapper mapper;
	static GetCustomerData customerData;
	static JsonPath message;
	static TestBase testBase;
	static String getCustomerJson;
	static CreateCustomerTest createCustomerTest;
	static String resposeMessage;

	private final static String testData = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
	private static XLS_Reader xlsx = new XLS_Reader(testData);
	public Hashtable<String, String> data;

	@Test(dataProvider = "Authentication")
	public static void GetCustomer(Map<Object, Object> map) throws JsonProcessingException {
		testBase = new TestBase();
		testBase.setUp();
		createCustomerTest = new CreateCustomerTest();
		customerData = new GetCustomerData();
		
		customerData.setCustomerId(String.valueOf(map.get("Customer_id")));
		
		ObjectMapper mapper = new ObjectMapper();
		String customerJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerData);
		
		httpreRequest = RestAssured.given();
		httpreRequest.headers(APIHeader.defaultHeader());
		httpreRequest.body(customerJson);

		Assert.assertEquals(response.statusCode(), 200);
		response = httpreRequest.get(APIUrl.getCustomerDataUrl + map.get("Customer_id"));
		
		log.info("Get Customer Data Test :- get Customer Json " + customerJson);
		log.info("Get Customer Data Test :- Header \"Authorization\" " + LoginTest.Accesstoken);
		log.info("Get Customer Data Test :- get Status Code " + response.statusCode());
		log.info("Get Customer Data Test :- get Customer url " + APIUrl.getCustomerDataUrl + map.get("Customer_id"));
		getParameter(APIUrl.getCustomerDataUrl+map.get("Customer_id"),customerJson);
		
		String customerBodyData = response.body().asString();

		message = new JsonPath(customerBodyData);
		reportingLogs(APIUrl.getCustomerDataUrl+map.get("Customer_id"));
		
		if (map.get("TestName").equals("ValidData")) {
			Assert.assertEquals(message.get("status_code"), 1004);
			Assert.assertEquals(message.get("sub_status_code"), 1004);
			Assert.assertEquals(message.get("message"), "Customer Data Retrieval Successfully");

		}
		if (map.get("TestName").equals("BlankCustID")) {
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5003);
			Assert.assertEquals(message.get("error"), "Customer Resource Not Found");
		}
		if (map.get("TestName").equals("InvalidCustID")) {
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5002);
		}
				
	}


	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("GetCustomer", xlsx);
		System.out.println(Arrays.deepToString(testObjArray));
		return testObjArray;

	}
}
