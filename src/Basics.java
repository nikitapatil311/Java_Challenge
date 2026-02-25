import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.AddPlace;
import files.ReUsable;


public class Basics {
	

	public static void main(String[] args) {
		
		//add place -> update place with new address -> get place to validate if new place is present in response
RestAssured.baseURI = "https://rahulshettyacademy.com"; 
String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type" , "application/json").body(
	AddPlace.Body()).when().post("/maps/api/place/add/json")
.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);
	String placeId = js.getString("place_id");
	System.out.println(placeId);
	//update place
	
	String newAddress = "70 wew walk, USA";
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\n"
			+ "\"place_id\": \""+placeId+ "\",\n"
			+ "\"address\":\""+ newAddress+"\",\n"
			+ "\"key\":\"qaclick123\"\n"
			+ "}\n"
			+ "").when().put("/maps/api/place/add/json")
	.then().assertThat().log().all().statusCode(200).body("msg" , equalTo("Address successfully updated"));
	
	//get place 
	
	String s1 = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
	System.out.println(s1);
	JsonPath js1 = ReUsable.rawJson(s1);
	String actualString = js1.getString("address");
	System.out.println(actualString);
	Assert.assertEquals(actualString,newAddress );
	
	
	}

}
