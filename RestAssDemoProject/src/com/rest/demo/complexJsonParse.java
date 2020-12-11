
package com.rest.demo;

import com.body.store.PayLoad;

import io.restassured.path.json.JsonPath;

public class complexJsonParse {
	public static void main(String[] args) {
		JsonPath js = new JsonPath(PayLoad.CoursePrice());
		//count
		int course=	js.getInt("courses.size()");
		System.out.println(course);

		//total amount
		int amt = js.getInt("dashboard.purchaseAmount");
		System.out.println(amt);

		//title of first element
		String title1 = js.get("courses[0].title");
		System.out.println(title1);


		//title of first element
		String title11 = js.getString("courses[0].title");
		System.out.println(title11);




	}

}
