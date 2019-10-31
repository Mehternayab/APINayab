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

public class Retweet {
	Logger Log = Logger.getLogger("Retweet");
	Properties prop ; 
	@BeforeTest
	public void getFile () throws IOException {
		PropertyConfigurator.configure("C:\\Users\\Online Test\\Desktop\\Mehter Nayab\\TwitterAPI\\src\\Twitter_Test\\log4j.properties");
		PropFile p = new PropFile();
		Properties prop = p.prop();
		this.prop = prop;
	}
	
	@Test
	public void Retweetthetweet() throws IOException {
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
		Response res = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret"))
		.queryParam("count", "1").
		when().get("home_timeline.json").then().extract().response();
		String str = res.asString();
		System.out.println(str);
		JsonPath js = new JsonPath(str);
		String text = js.get("text").toString();
		System.out.println(text);
		Log.info(text);
		
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
		Response res1 = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		queryParam("status",text+" "+"#Qualitest").
		when().post("update.json").then().extract().response();
		String str1 = res1.asString();
		System.out.println(str1);
		JsonPath js1 = new JsonPath(str1);
		String id = js1.get("id").toString();
		System.out.println(id);
		
		given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		when().post("/destroy/"+id+".json").then().assertThat().statusCode(200);
	}
}