package files;

import io.restassured.path.json.JsonPath;

public class ReUsable {

	public static JsonPath rawJson(String response) {
JsonPath js1 = new JsonPath(response);
return js1;
	}

}
