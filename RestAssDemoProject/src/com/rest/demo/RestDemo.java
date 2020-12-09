package com.rest.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.body.store.PayLoad;
import com.body.store.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RestDemo {

	public static void main(String[] args) {
		//GIVEN - ALL INPUT DETAILS
		//WHEN - SUBMIT THE API
		//THEN - VALIDATE THE RESPONSE
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(PayLoad.AddPlace1())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)")
				.extract().response().asString();

		System.out.println("Response in String formate :---"+response);
		JsonPath js = new JsonPath(response);
		String placeID= js.getString("place_id");
		System.out.println("Place ID value "+placeID);


		//update

		String newAddress = "Bangalore,Karnataka,India";


		given().log().all().queryParam("key","qaclick12").header("Content-Type","application/json").body("{\r\n" +
				"    \"place_id\":\""+placeID+"\",\r\n" +
				"    \"address\":\""+newAddress+"\",\r\n" +
				"    \"key\":\"qaclick123\"\r\n" +
				"}").
		when().put("/maps/api/place/update/json").
		then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


		//get

		String newAdd = given().log().all().queryParam("key", "qaclick123").
				queryParam("place_id",placeID).
				when().get("/maps/api/place/get/json").
				then().assertThat().log().all().statusCode(200).extract().response().asString();

		System.out.println("Response in String formate :---"+newAdd);
		JsonPath jp = ReusableMethods.rawToJson(newAdd);
		//JsonPath js1 = new JsonPath(newAdd);
		String newAddd= jp.getString("address");
		System.out.println("address value "+newAddd);

































	}

}
