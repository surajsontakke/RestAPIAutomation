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
import com.java.qa.sccm.pojopages.DeleteCustomer;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DeleteCustomerTest extends TestBase {

	TestBase testbase;
	CreateCustomerTest createCustomerTest;
	DeleteCustomer deleteCustomer;
	public static JsonPath message;
	public static String resposeMessage;
	private final static String testData = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
	private static XLS_Reader xlsx = new XLS_Reader(testData);
	public Hashtable<String, String> data;

	@Test(dataProvider = "Authentication")
	public void DeleteCustomer(Map<Object, Object> map) throws JsonProcessingException {
		testbase = new TestBase();
		testbase.setUp();

		createCustomerTest = new CreateCustomerTest();
		deleteCustomer = new DeleteCustomer();

		deleteCustomer.setCustomer_id(String.valueOf(map.get("Customer_id")));
		ObjectMapper mapper = new ObjectMapper();
		String deleteCustomerJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(deleteCustomer);

		httpreRequest = RestAssured.given().log().all();
		httpreRequest.headers(APIHeader.defaultHeader());
		httpreRequest.body(deleteCustomerJson);

		Assert.assertEquals(response.statusCode(), 200);

		response = httpreRequest.delete(APIUrl.deleteCustomerUrl + map.get("Customer_id"));

		log.info("Delete Customer API:- Delete the response Body for Delete Customer URL "
				+ httpreRequest.delete(APIUrl.deleteCustomerUrl + map.get("Customer_id")));
		getParameter(APIUrl.deleteCustomerUrl,"Delete Customer Data "+deleteCustomerJson);

		String DeletecustomerData = response.body().asString();
		System.out.println(DeletecustomerData);
		log.info("Delete Customer API:-" + DeletecustomerData);
		message = new JsonPath(DeletecustomerData);
		reportingLogs(APIUrl.deleteCustomerUrl+ map.get("Customer_id"));
		
		if (map.get("TestName").equals("ValidData")) {
			Assert.assertEquals(message.get("status_code"), 200);
			Assert.assertEquals(message.get("sub_status_code"), 2000);
			Assert.assertEquals(message.get("message"), "Customer Deleted Successfully");
		}
		if (map.get("TestName").equals("BlankCustID")) {
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5003);
		}
		if (map.get("TestName").equals("InvalidCustID")) {
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5003);
		}

	}


	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("DeleteCustomer", xlsx);
		return testObjArray;

	}
}
