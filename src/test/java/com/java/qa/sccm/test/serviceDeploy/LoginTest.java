package com.java.qa.sccm.test.serviceDeploy;

import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.qa.sccm.Utility.ExcelUtils;
import com.java.qa.sccm.Utility.ExtendReportListener;
import com.java.qa.sccm.Utility.XLS_Reader;
import com.java.qa.sccm.base.APIUrl;
import com.java.qa.sccm.base.TestBase;
import com.java.qa.sccm.pojopages.Login;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LoginTest extends TestBase {

	public LoginTest() {
		super();
	}

	public ExtentTest test;
	public Login login;
	public static String Accesstoken;
	TestBase testbase;
	private final static String testData = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
	private static XLS_Reader xlsx = new XLS_Reader(testData);
	public Hashtable<String, String> data;

	@Test(dataProvider = "Authentication", description = "Verify sign in functionality")
	public void loginTestValidation(Map<Object, Object> map) throws JsonProcessingException {
		login = new Login();
		TestBase.setUp();
		login.setUsername(String.valueOf(map.get("UserName")));
		login.setPassword(String.valueOf(map.get("Password")));

		// mapper = new ObjectMapper();
		// LoginJson =
		// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(login);

		log.info("Login API :- Add the Login json data into json format ");

		httpreRequest = RestAssured.given().log().all();
		httpreRequest.header("Content-Type", "application/json");
		httpreRequest.body(login);
		response = httpreRequest.post(APIUrl.postLoginUrl);

		log.info("Login API:- " + APIUrl.postLoginUrl);
		Assert.assertEquals(response.getStatusCode(), 200);

		JsonPath message = response.jsonPath();

		log.info("Login API :- get Login response " + message.get());
		// String token = message.get("access_token");
		// String tokenType = message.get("token_type");
		Accesstoken = message.get("token_type") + " " + message.get("access_token");
		log.info("Login API:- get the Accesstoken for login URL " + Accesstoken);

		
		if (map.get("TestName").equals("LoginTest")) {
			Assert.assertEquals(message.get("message"), "Data retrieval successfully");
			Assert.assertEquals(message.get("status"), true);
		} else {
			Assert.assertEquals(message.get("status"), false);
			Assert.assertEquals(message.get("message"), "Data retrieval successfully");
		}
	}

	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("Login", xlsx);
		return testObjArray;

	}

}
