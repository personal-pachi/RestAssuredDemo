package com.body.store;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	public static JsonPath rawToJson(String raw) {
		JsonPath js1 = new JsonPath(raw);
		return js1;

	}

}
