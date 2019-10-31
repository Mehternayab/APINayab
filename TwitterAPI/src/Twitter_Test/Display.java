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

public class Display {
	Logger Log = Logger.getLogger("Display");
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
		RestAssured.baseURI=prop.getProperty("baseUriOfDisplay");
		Response res = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		when().get(prop.getProperty("resourceOfDisplay")).then().extract().response();
		String str = res.asString();
		Log.info(str);
		System.out.println(str);
		JsonPath js = new JsonPath(str);
		String id = js.get("id").toString();
		Log.info(id);
		System.out.println(id);
		String name = js.get("text").toString();
		Log.info(name);
		System.out.println(name);
	}

}
