package Twitter_Test;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Hashtags {
	int[] a = {1,2295383,28218,23424977,23424852};
	Logger Log = Logger.getLogger("Hashtags");
	Properties prop ; 
	
	@BeforeTest
	public void getFile () throws IOException {
		PropertyConfigurator.configure("C:\\Users\\Online Test\\Desktop\\Mehter Nayab\\TwitterAPI\\src\\Twitter_Test\\log4j.properties");
		PropFile p = new PropFile();
		Properties prop = p.prop();
		this.prop = prop;
	}
	
	@Test
	public void Trends() throws IOException {
		for(int i=0 ; i < a.length ; i++) {
		RestAssured.baseURI=prop.getProperty("baseUriOfHashtags");
		Response res = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret"))
		.queryParam("id", a[i]).
		when().get(prop.getProperty("resourceOfHashtags")).then().extract().response();
		String str = res.asString();
		System.out.println(str);
		JsonPath js = new JsonPath(str);
		String text = js.get("trends[0].name").toString();
		System.out.println(text);
		Log.info(text);
		
		}
	}
}
