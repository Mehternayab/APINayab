package Twitter_Test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

public class CreateAndDelete {
	Logger Log = Logger.getLogger("CreateAndDelete");
	Properties prop ; 
	@BeforeTest
	public void getFile () throws IOException {
		PropertyConfigurator.configure("C:\\Users\\Online Test\\Desktop\\Mehter Nayab\\TwitterAPI\\src\\Twitter_Test\\log4j.properties");
		PropFile p = new PropFile();
		Properties prop = p.prop();
		this.prop = prop;
	}
	
	@Test
	public void TestPost() throws IOException {
		RestAssured.baseURI=prop.getProperty("baseUriOfCreate");
		Response res = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		queryParam("status","I am learning API testing using RestAssured  Java by nayab #Qualitest").
		when().post(prop.getProperty("resourceOfCreate")).then().extract().response();
		String str = res.asString();
		System.out.println(str);
		JsonPath js = new JsonPath(str);
		String id = js.get("id").toString();
		Log.info(id);
		System.out.println(id);
		
		given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		when().post("/destroy/"+id+".json").then().assertThat().statusCode(200);
		
		Response res1 = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		queryParam("count","1").
		when().get(prop.getProperty("resourceOfDisplay")).then().extract().response();
		String str1 = res1.asString();
		System.out.println(str1);
		JsonPath js1 = new JsonPath(str1);
		String id1 = js1.get("id").toString();
		Log.info(id1);
		System.out.println(id1);
		String name = js1.get("text").toString();
		System.out.println(name);
	}
}
