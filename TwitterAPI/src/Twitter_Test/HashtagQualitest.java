package Twitter_Test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.fasterxml.jackson.databind.util.TypeKey;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

//import java.util.HashMap;

public class HashtagQualitest {
	Logger Log = Logger.getLogger("HashtagQualitest");
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
		RestAssured.baseURI=prop.getProperty("baseUriOfHashtag");
		Response res = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		queryParam("q", "#Qualitest").
		when().get(prop.getProperty("resourceOfHashtag")).then().extract().response();
		String str = res.asString();
		System.out.println(str);
		JsonPath js = new JsonPath(str);
		String name = js.get("statuses[0].user.name").toString();
		System.out.println(name);
		Log.info(name);
		String text = js.get("statuses[0].text").toString();
		Log.info(text);
		System.out.println(text);
		/*HashMap<String, String> name1 = js.get("statuses");
		for (String name: name1.keySet()){
	            String key = name.toString();
	            String value = name1.get(name).toString();  
	            System.out.println(key + " " + value);  
		} */
	}
}
