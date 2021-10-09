package com.java.qa.sccm.test;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

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
import com.java.qa.sccm.pojopages.UpdateCustomer;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class UpdateCustomerTest extends TestBase {

	UpdateCustomerTest() {
		super();
	}

	public TestBase testbase;
	public ObjectMapper mapper;
	public LoginTest logTest;
	public UpdateCustomer updateCustomer;
	public static JsonPath message;
	private static String resposeMessage;

	public Hashtable<String, String> data;
	private final static String testData = System.getProperty("user.dir") + "/TestData/TestData.xlsx";
	private static XLS_Reader xls = new XLS_Reader(testData);

	@Test(dataProvider = "Authentication")
	public void updateCustomer(Map<Object, Object> map) throws JsonProcessingException {
		testbase = new TestBase();
		testbase.setUp();
		Random r = new Random();
		int low = 1;
		int high = 1000;
		String Test = "NEWAPI";
		String result = Test + r.nextInt(high - low);

		// int customerid = createCustomerTest.customerID;

		updateCustomer = new UpdateCustomer();
		updateCustomer.setCustomerName(String.valueOf(result));

		mapper = new ObjectMapper();
		String UpdateCustomerJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateCustomer);

		log.info("update Customer Data Test :- Update Customer Json " + UpdateCustomerJson);
		httpreRequest = RestAssured.given();
		httpreRequest.headers(APIHeader.defaultHeader());

		log.info("update Customer Data Test :- Header \"Authorization\" " + logTest.Accesstoken);

		httpreRequest.body(map.get("Customer_id"));
		
		httpreRequest.body(UpdateCustomerJson);

		log.info("update Customer Data Test :- get Status Code " + response.statusCode());
		response = httpreRequest.put(APIUrl.putUpdateCustomerUrl + map.get("Customer_id"));
		log.info("update Customer Data Test :- get Customer url " + APIUrl.putUpdateCustomerUrl
				+ map.get("Customer_id"));
		getParameter(APIUrl.putUpdateCustomerUrl + map.get("Customer_id"),UpdateCustomerJson);
		String UpdateCustomerBodyData = response.body().asString();
		System.out.println(UpdateCustomerBodyData);

		message = new JsonPath(UpdateCustomerBodyData);
		reportingLogs(APIUrl.putUpdateCustomerUrl + map.get("Customer_id"));

		if (map.get("TestName").equals("validData")) {
			Assert.assertEquals(message.get("status_code"), 1002);
			Assert.assertEquals(message.get("sub_status_code"), 1002);
			Assert.assertEquals(message.get("message"), "Updated Successfully");
		}
		if (map.get("TestName").equals("BlankCustID")) {
			Assert.assertEquals(message.get("error"), "customer_id cannot be empty");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5002);
		}
		if (map.get("TestName").equals("InvalidCustID")) {
			Assert.assertEquals(message.get("error"), "Invalid customer_id");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5003);
		}
	}

	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("updateCustomer", xls);
		return testObjArray;

	}
}
