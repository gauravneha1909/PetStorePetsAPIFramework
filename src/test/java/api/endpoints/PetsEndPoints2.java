package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import api.payload.Pets;
import io.restassured.response.Response;


public class PetsEndPoints2 {
	
	
	static ResourceBundle getURL() 
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	

	public static Response  getpetinfo(int petid)
	
	{
		String get_petbyid= getURL().getString("get_petbyid");
		
		Response response=given()
		.header("accept","application/json")
		.pathParam("petid", petid)
		
		.when()
		.get(get_petbyid);
		
		return  response;

		
	}

	
	public static Response deletepet(int petid)
	
	{
		String delete_pet= getURL().getString("delete_pet");
		
		Response response=given()
		.header("accept","application/json")
		.pathParam("petid", petid)
		
		.when()
		.get(delete_pet);
		
		return  response;

	}
		

	
	public static Response uploadpetimage(String filepath, int petid)
	
	{
		
		String post_upload_image= getURL().getString("post_upload_image");
		
	File myfile=new File(filepath);
	

	
	
		Response response=
				
				given()
		.header("accept","application/json")
		.contentType("multipart/form-data")
		.multiPart("file", myfile) // Specify file parameter name, file object, and content type
		.pathParam("petid", petid)
		
		.when()
		.post(post_upload_image)
		;
		
		return  response;

		
	}


		
	public static Response addnewpet(Pets pets)

			
			{
		
		String post_add_pet= getURL().getString("post_add_pet");
				Response response=
						
				given()
				.header("accept","application/json")
				.header("Content-Type","application/json")
				.body(pets)
				

				.when()
				.post(post_add_pet)
				;
				
				return  response;

				
			}
	
	
	public static Response getpetsbystatus(String status)
	
	{
		
		String get_petbyStatus= getURL().getString("get_petbyStatus");
		
		Response response=given()
				
			.accept("application/json")	
			.queryParam("status", status)
		
		.when()
		.get(get_petbyStatus);
		
		
		return response;
		
		
		
		
		
	}
	
	
	public static Response updatepet(Pets pets)


	
	{
		String put_update_pet= getURL().getString("put_update_pet");
		
		Response response=
				
		given()
		.header("accept","application/json")
		.contentType("application/json")
		.body(pets)

		.when()
		.put(put_update_pet)
		;
		
		return  response;

		
	}



	public static Response updatepetdata(int petid, String status, String name)
	
	{
		String post_petupdatewithformdata= getURL().getString("post_petupdatewithformdata");
		
		 Map<String, Object> formdata = new HashMap<>();
		 formdata.put("name", name);
		 formdata.put("status", status);
		
		Response response=given()
		
		
		.when()
		.header("accept","application/json")
		.contentType("application/x-www-form-urlencoded")
		.pathParam("petid", petid)
		.formParams(formdata)
		
		.post(post_petupdatewithformdata);
		
		return response;
		
		
	}


}
