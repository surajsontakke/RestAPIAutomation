package API_Autiomation.SSCM_Automation;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.java.qa.sccm.Utility.ExcelUtils;
import com.java.qa.sccm.Utility.XLS_Reader;
import com.java.qa.sccm.base.APIUrl;
import com.java.qa.sccm.base.TestBase;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestLogin {
	TestBase testbase;
	public static String token;
	private final static String testData = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
	private static XLS_Reader xlsx = new XLS_Reader(testData);
	public Hashtable<String, String> data;

	@Test(dataProvider = "Authentication")
	public void loginTest(Map<Object, Object> map) {
		Login l = new Login();
		testbase = new TestBase();
		TestBase.setUp();
		l.setUsername(String.valueOf(map.get("UserName")));
		l.setPassword(String.valueOf(map.get("Password")));

		RestAssured.baseURI = APIUrl.baseUrl;

		String res = given().queryParam("key", "SSCM")
				.header("Content-type", "application/x-www-form-urlencoded", "Accept", "application/json").body(l).log()
				.all().when().post(APIUrl.LoginPostUrl).then().assertThat().statusCode(200).assertThat().log().all()
				.extract().asString();

		JsonPath js = new JsonPath(res);

		String tokenType = js.get("token_type");
		String accessToke = js.get("access_token");
		token = tokenType + " " + accessToke;
		System.out.println(token);

		Assert.assertEquals(js.get("status"), true);
		Assert.assertEquals(js.get("message"), "Data retrieval successfully");
		Assert.assertEquals(js.get("token_type"), "Bearer");
	}

	@DataProvider()
	public Object[][] Authentication() throws Exception {

		Object[][] testObjArray = ExcelUtils.getData("Login", xlsx);

		//System.out.println(Arrays.deepToString(testObjArray));

		return testObjArray;

	}
}
