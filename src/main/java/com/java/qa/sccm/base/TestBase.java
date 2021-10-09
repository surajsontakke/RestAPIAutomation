package com.java.qa.sccm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpConnection;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Connection.Request;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.internal.annotations.DataProviderAnnotation;

import com.java.qa.sccm.Utility.ExtendReport;
import com.java.qa.sccm.Utility.ExtendReportListener;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.RequestLogSpecificationImpl;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

@Listeners(ExtendReport.class)
public class TestBase extends ExtendReport {

	public static RequestSpecification httpreRequest;
	public static Response response;
	public static ResponseBody responseBody;
	public static Logger log;
	public static Properties prop;
	public static FileInputStream fis;
	public static String resposeMessage;

	public TestBase() {
		prop = new Properties();
		try {
			fis = new FileInputStream(
					"C:\\Users\\susontak\\eclipse-workspace\\SSCM-Automation\\Resources\\config.properties");
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		try {
			prop.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void setUp() {

		log = Logger.getLogger("SCCM Automation Logs : ");

		PropertyConfigurator.configure("log4j.properties");

	}

	public static String getUsername() {

		String Username = prop.getProperty("SetUsername");
		return Username;

	}

	public static String getPassword() {

		String Password = prop.getProperty("SetPassword");
		return Password;

	}

	
	public static String resposeBody() {
		return "Response Messege : " + response.getBody().asPrettyString();
	}

	public static String getStatusCode() {
		return "Status Code : " + String.valueOf(response.getStatusCode());
	}

	public static void getParameter(String Url, String value) {
		test.log(LogStatus.INFO, "Request URI : " + APIUrl.baseUrl + RequestSpecificationImpl.getDerivedPath(Url));
		test.log(LogStatus.INFO, getStatusCode());
		test.log(LogStatus.INFO, value);
		if (LogStatus.PASS != null) {
			test.log(LogStatus.PASS, resposeBody());
		} else {
			test.log(LogStatus.FAIL, resposeBody());
		}

	}

	public static String reportingLogs(String url) {
		Reporter.log("Request URI : " + url);
		Reporter.log(String.valueOf("Status code : " + response.getStatusCode()));
		Reporter.log("Response message : " + resposeBody());
		return resposeMessage;
	}

}
