package api.endpoints;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.payload.Category;
import api.payload.Pets;
import api.payload.Tag;
import io.restassured.response.Response;

import io.restassured.matcher.*;


public class PetsEndPoints {
	

	public static Response  getpetinfo(int petid)
	
	{
		Response response=given()
		.header("accept","application/json")
		.pathParam("petid", petid)
		
		.when()
		.get(Routes.get_petbyid);
		
		return  response;

		
	}

	
	public static Response deletepet(int petid)
	
	{
		Response response=given()
		.header("accept","application/json")
		.pathParam("petid", petid)
		
		.when()
		.get(Routes.delete_pet);
		
		return  response;

	}
		

	
	public static Response uploadpetimage(String filepath, int petid)
	
	{
		
	
	File myfile=new File(filepath);
	

	
	
		Response response=
				
				given()
		.header("accept","application/json")
		.contentType("multipart/form-data")
		.multiPart("file", myfile) // Specify file parameter name, file object, and content type
		.pathParam("petid", petid)
		
		.when()
		.post(Routes.post_upload_image)
		;
		
		return  response;

		
	}


		
	public static Response addnewpet(Pets petdata, Tag tagdata, Category catdata)

			
			{
				Response response=
						
				given()
				.header("accept","application/json")
				.contentType("application/json")
				.body(petdata)
				.body(tagdata)
				.body(catdata)
				

				.when()
				.post(Routes.post_add_pet)
				;
				
				return  response;

				
			}
	
	
	public static Response getpetsbystatus(String status)
	
	{
		
		Response response=given()
				
			.accept("application/json")	
			.queryParam("status", status)
		
		.when()
		.get(Routes.get_petbyStatus);
		
		
		return response;
		
		
		
		
		
	}
	
	
	public static Response updatepet(Pets petdata, Tag tagdata, Category catdata)


	
	{
		Response response=
				
		given()
		.header("accept","application/json")
		.contentType("application/json")
		.body(petdata)
		.body(tagdata)
		.body(catdata)
		

		.when()
		.put(Routes.put_update_pet)
		;
		
		return  response;

		
	}


	public static Response updatepetdata(int petid, String status, String name)
	
	{
		
		 Map<String, Object> formdata = new HashMap<>();
		 formdata.put("name", name);
		 formdata.put("status", status);
		
		Response response=given()
		
		
		.when()
		.header("accept","application/json")
		.contentType("application/x-www-form-urlencoded")
		.pathParam("petid", petid)
		.formParams(formdata)
		
		.post(Routes.post_petupdatewithformdata);
		
		return response;
		
		
	}


}
