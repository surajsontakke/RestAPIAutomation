package com.java.qa.sccm.test;

import java.util.Hashtable;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.java.qa.sccm.Headers.APIHeader;
import com.java.qa.sccm.Utility.ExcelUtils;
import com.java.qa.sccm.Utility.XLS_Reader;
import com.java.qa.sccm.base.APIUrl;
import com.java.qa.sccm.base.TestBase;
import com.java.qa.sccm.pojopages.EndpointDevice;
import com.java.qa.sccm.pojopages.Parameters;
import com.java.qa.sccm.pojopages.Step1;
import com.java.qa.sccm.pojopages.Step2Transaction;
import com.mongodb.util.JSON;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

/*{
"transaction_id": 5345,
"endpoint_devices": [
{
"device_id": "1000",
"service_specific_id": "1227356323",
"adl": null,
"parameters": {
"customer_id": "10034567",
"route-map_name": "xyz",
"ipv6_address_+_subnet_mask": "235",
"topology_type": "1"
}
}
]
} */

public class Step2Test extends TestBase {

	TestBase testbase;
	LoginTest loginTest;
	public static JsonPath message;
	private static String resposeMessage;
	private final static String testData = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
	private static XLS_Reader xlsx = new XLS_Reader(testData);
	public Hashtable<String, String> data;

	@Test(dataProvider = "Authentication")
	public void ValidationStep2(Map<Object, Object> map) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		testbase = new TestBase();
		testbase.setUp();
		Parameters parameters = new Parameters();
		parameters.setCustomerId(String.valueOf(map.get("customer_id")));
		parameters.setIpv6AddressSubnetMask(String.valueOf(map.get("ipv6_address_+_subnet_mask")));
		parameters.setTopologyType(String.valueOf(map.get("topology_type")));

		EndpointDevice step2EndpointDevice = new EndpointDevice();
		step2EndpointDevice.setDeviceId(String.valueOf(map.get("device_id")));
		step2EndpointDevice.setParameters(parameters);
		step2EndpointDevice.setServiceSpecificId(String.valueOf(map.get("service_specific_id")));
		step2EndpointDevice.setAdl(String.valueOf(map.get("adl")));

		Step2Transaction step2Transaction = new Step2Transaction();
		step2Transaction.setTransactionId(String.valueOf(map.get("transaction_id")));
		//step2Transaction.getEndpointDevices();
		String Step2Json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(step2Transaction);
		httpreRequest = RestAssured.given().log().all();
		httpreRequest.headers(APIHeader.defaultHeader());

		httpreRequest.body(Step2Json);
		response = httpreRequest.post(APIUrl.postServiceDeployStep2);
		getParameter(APIUrl.postServiceDeployStep2, Step2Json);
		message = new JsonPath(response.getBody().asString());
		String Step2Body = response.body().asString();
		System.out.println(Step2Body);
		JsonPath js = new JsonPath(Step2Body);
		System.out.println("Step2 response payload : " + js.get());

		reportingLogs(APIUrl.postServiceDeployStep2);

	}

	@DataProvider()
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils.getData("Step2", xlsx);
		return testObjArray;

	}

}
