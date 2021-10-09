package com.java.qa.sccm.test;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.qa.sccm.Utility.ExcelUtils;
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
	public ExtentReports extent;
	public static JsonPath message;
	public Login login;
	public static String Accesstoken;
	public static String resposeMessage;
	public TestBase testbase;
	private final static String testData = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
	private static XLS_Reader xlsx = new XLS_Reader(testData);
	public Hashtable<String, String> data;

	@Test(dataProvider = "Authentication", description = "Verify sign in functionality")
	public void loginTestValidation(Map<Object, Object> map) throws Exception {

		TestBase.setUp();
		login = new Login();
		login.setUsername(String.valueOf(map.get("UserName")));
		login.setPassword(String.valueOf(map.get("Password")));

		ObjectMapper mapper = new ObjectMapper();
		String loginJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(login);

		httpreRequest = RestAssured.given().log().all();
		httpreRequest.header("Content-Type", "application/json");
		httpreRequest.body(loginJson);
		
		response = httpreRequest.post(APIUrl.postLoginUrl);

		log.info("Login API:- " + APIUrl.postLoginUrl);

		Assert.assertEquals(response.getStatusCode(), 200);
		message = response.jsonPath();
		log.info("Login API :- get Login response " + message.get().toString());
		Accesstoken = message.get("token_type") + " " + message.get("access_token");
		

		reportingLogs(APIUrl.postLoginUrl);

		if (map.get("TestName").equals("LoginTest")) {
			Assert.assertEquals(message.get("message"), map.get("ExpectedOutput"));
			Assert.assertEquals(message.get("status"), true);
		}
		getParameter(APIUrl.postLoginUrl,loginJson);
	}

	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("Login", xlsx);
		return testObjArray;
	}
}
