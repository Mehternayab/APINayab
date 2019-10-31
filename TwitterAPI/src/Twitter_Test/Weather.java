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


public class Weather {
	Logger Log = Logger.getLogger("Weather");
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
		RestAssured.baseURI=prop.getProperty("baseUriOfWeather");
		Response res = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		queryParam("q", "bangalore weather").
		when().get(prop.getProperty("resourceOfWeather")).then().extract().response();
		String str = res.asString();
		System.out.println(str);
		JsonPath js = new JsonPath(str);
		String text = js.get("statuses[0].text").toString();
		System.out.println(text);
		Log.info(text);
	}

}
