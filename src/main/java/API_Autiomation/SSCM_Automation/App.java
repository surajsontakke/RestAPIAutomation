package API_Autiomation.SSCM_Automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import com.java.qa.sccm.base.TestBase;

/**
 * Hello world!
 *
 */
public class App extends TestBase {

	@Test
	public static void Test() {
		log.info("*****************Login Test************************");
		RestAssured.baseURI = "http://173.37.21.174/SCCM/sccm_api/web";
		String response = given().log().all().queryParam("key", "SSCM")
				.header("Content-type", "application/x-www-form-urlencoded", "Accept", "application/json")
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\": \"Test@321\"\r\n" + "}").when()
				.post("/api/v1/login").then().assertThat().statusCode(200).body("status", equalTo(true))
				.extract().response().asString();

		JsonPath js = new JsonPath(response); // for parsing json
		String TokenTyepeMessage = js.get("token_type");
		String AccessToken = js.get("access_token");
		String Authorization_Token = TokenTyepeMessage + " " + AccessToken;
		log.info("*****************Login Test Successful*****************");

		log.info("*****************Create customer Test************************");
		String customerPostData = given().log().all().queryParam("key", "SSCM")
				.header("Authorization", "" + Authorization_Token + "")
				.body("{\r\n" + " \"customer_id\":\"12346\",\r\n" + " \"customer_name\": \"SurahTest\",\r\n"
						+ "  \"short_name\":\"APITest\",\r\n" + " \"app_data\":{}\r\n" + "}")
				.when().post("/api/v1/customers").then().log().all().statusCode(200).body("status_code", equalTo(5000))
				.extract().response().asString();
		
		log.info("*****************customer Test completed************************");
		String customerGetData = given().log().all().queryParam("key", "SSCM")
				.header("Authorization", "" + Authorization_Token + "").body("{\"customer_id\": \"12346\"} ").when()
				.get("api/v1/customers").then().log().all().assertThat().statusCode(200)
				.body("status_code", equalTo(5000)).extract().response().asString();
		// JsonPath js1 = Resusbale.rawToJson(customerGetData);
		// Assert.assertEquals(js1.get("status"), 401);
		log.info("*****************Get Customer data Test************************");
		System.out.println("Response of API :- " + response);
		System.out.println("Post customer Data :- " + customerPostData);
		System.out.println("Get customer  Data :- " + customerGetData);
	}
}
