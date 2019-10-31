package Twitter_Test;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Block {
	Properties prop ; 
	Logger Log = Logger.getLogger("Block");
	@BeforeTest
	public void getFile () throws IOException {
		PropertyConfigurator.configure("C:\\Users\\Online Test\\Desktop\\Mehter Nayab\\TwitterAPI\\src\\Twitter_Test\\log4j.properties");
		PropFile p = new PropFile();
		Properties prop = p.prop();
		this.prop = prop;
	}
	
	@Test
	public void block1() throws IOException {
		RestAssured.baseURI=prop.getProperty("baseUriOfBlock");
		Response res = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		queryParam("screen_name", "MehterNayab").
		when().post(prop.getProperty("resourceOfBlock")).then().extract().response();
		String str = res.asString();
		Log.info(str);
		System.out.println("Blocked user id = "+str);
		
		Response res1 = given().auth().oauth(prop.getProperty("ck"), prop.getProperty("cs"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		queryParam("screen_name", "MehterNayab").
		when().post(prop.getProperty("resourceOfDestory")).then().extract().response();
		String str1 = res1.asString();
		Log.info(str1);
		System.out.println("Unblocked user id = "+str1);
	}
}
