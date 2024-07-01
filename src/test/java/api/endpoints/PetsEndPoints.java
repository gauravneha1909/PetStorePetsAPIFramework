package api.endpoints;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import io.restassured.matcher.*;


public class PetsEndPoints {
	
	@Test(priority=1)
	void  getpetinfo()
	
	{
		Response response=given()
		.header("accept","application/json")
		.pathParam("petid", "1")
		
		.when()
		.get(Routes.get_petbyid);
		
		//return  response;

		
	}

	
	@Test(priority=2)
	void deletepet()
	
	{
		Response response=given()
		.header("accept","application/json")
		.pathParam("petid", "1")
		
		.when()
		.get(Routes.delete_pet);
		
		//return  response;

	}
		
		@Test(priority=3)
	
void uploadpetimage()
	
	{
		
	
	File myfile=new File("/Users/gauravkumar/dog.jpg");
	

	
	
		//Response response=
				
				given()
		.header("accept","application/json")
		.contentType("multipart/form-data")
		.multiPart("file", myfile) // Specify file parameter name, file object, and content type
		.pathParam("petid", "1")
		
		.when()
		.post(Routes.post_upload_image)
		.then()
		.statusCode(200)
		.log().all()
		;
		
		//return  response;

		
	}

	
	
	
	
}
