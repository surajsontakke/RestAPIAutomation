package com.java.qa.sccm.test;

import static org.testng.Assert.assertThrows;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.ITestResult;
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
import com.java.qa.sccm.pojopages.CreateCustomer;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateCustomerTest extends TestBase {

	CreateCustomerTest() {
		super();
	}

	private static CreateCustomer createCustomer;
	private static ObjectMapper mapper;
	private static LoginTest logTest;
	static int customerID;
	private static JsonPath message;
	private static TestBase testbase;
	public static String resposeMessage;
	public Hashtable<String, String> data;
	private final static String testData = System.getProperty("user.dir") + "/TestData/TestData.xlsx";
	private static XLS_Reader xls = new XLS_Reader(testData);

	@Test(priority = 1, dataProvider = "Authentication")
	public static void createCustomer(Map<Object, Object> map) throws JsonProcessingException {
		testbase = new TestBase();
		testbase.setUp();
		logTest = new LoginTest();
		Random r = new Random();
		int low = 1;
		int high = 1000000;
		customerID = r.nextInt(high - low);
		int result = r.nextInt(high - low);
		System.out.println(result);
		createCustomer = new CreateCustomer();

		createCustomer.setCustomerId(map.get("customer_id") + String.valueOf(customerID));
		log.info("Create Customer Test :- set the Customer ID " + customerID);
		createCustomer.setCustomerName(map.get("customer_name") + String.valueOf(result));
		createCustomer.setShortName(map.get("short_name") + String.valueOf(result));
		createCustomer.setAppData(null);

		mapper = new ObjectMapper();
		String createCustomerJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(createCustomer);
		System.out.println(createCustomerJson);
		log.info("Create Customer Test :- create the Customer data into json body " + createCustomerJson);
		httpreRequest = RestAssured.given().log().all();
		httpreRequest.headers(APIHeader.defaultHeader());
		// System.out.println(httpreRequest.headers(APIHeader.defaultHeader()));
		log.info("Create Customer Test :-  Header \"Authorization\"" + logTest.Accesstoken);

		httpreRequest.body(createCustomerJson);

		log.info("Create Customer Test :-  get Status Code " + response.statusCode());
		response = httpreRequest.post(APIUrl.postCreateCustomerUrl);
		log.info("Create Customer Test :-  Post url " + APIUrl.postCreateCustomerUrl);
		getParameter(APIUrl.postCreateCustomerUrl,"Create Customer Data: "+createCustomerJson);
		String customerBody = response.body().asString();
		System.out.println(customerBody);
		log.info("Create Customer Test :-  customer Body response " + customerBody);
		message = new JsonPath(customerBody);

		reportingLogs(APIUrl.postCreateCustomerUrl);
		

		if (map.get("TestName").equals("ValidData")) {
			Assert.assertEquals(message.get("message"), "Customer Created Successfully");
			Assert.assertEquals(message.get("status_code"), 1001);
			Assert.assertEquals(message.get("sub_status_code"), 1001);
		}
		if (map.get("TestName").equals("BlankCustName")) {
			Assert.assertEquals(message.get("error"), "customer_name cannot be empty");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5002);
		}
		if (map.get("TestName").equals("InvalidCustName")) {
			Assert.assertEquals(message.get("error"), "Kindly provide valid values of customer_name");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5003);
		}
		if (map.get("TestName").equals("BlankCustID")) {
			Assert.assertEquals(message.get("error"), "customer_id cannot be empty");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5002);
		}
		if (map.get("TestName").equals("InvalidCustID")) {
			Assert.assertEquals(message.get("error"), "Kindly provide valid values of customer_id");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5003);
		}
		if (map.get("TestName").equals("BlankShortName")) {
			Assert.assertEquals(message.get("error"), "short_name cannot be empty");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5002);
		}
		if (map.get("TestName").equals("ShortNameRangeHigh")) {
			Assert.assertEquals(message.get("error"), "Short Name Should Contain At Most 15 Characters.");
			Assert.assertEquals(message.get("status_code"), 5000);
			Assert.assertEquals(message.get("sub_status_code"), 5003);
		}
	}


	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("CreateCustomer", xls);
		return testObjArray;
	}
}
