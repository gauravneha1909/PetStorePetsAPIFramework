package api.pettests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;

import api.endpoints.PetsEndPoints;
import api.payload.Category;
import api.payload.Pets;
import api.payload.Tag;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class PetTestData {
	
	
	Category category;
	
	Pets pets;
	
	Tag tag;
	
	Faker faker;
	

	@Test (priority=1, dataProvider="PetData",dataProviderClass=DataProviders.class)

	public void addNewPet(String PetID,String CategoryID,String CategoryName, String PetName,String PhotoURL, String TagID, String TagName,String PetStatus) 
	{
		
		// Convert String parameters to int if needed
        int petId = Integer.parseInt(PetID);
        int categoryId = Integer.parseInt(CategoryID);
        int tagId = Integer.parseInt(TagID);
        
        String[] photoUrls = PhotoURL.split(",");
		
		category=new Category();
		category.setId(categoryId);
		category.setName(CategoryName);
		
		tag =new Tag();
		tag.setId(tagId);
		tag.setName(TagName);
		
		pets = new Pets();
		pets.setId(petId);
		pets.setName(PetName);
		pets.setPhotoUrls(photoUrls);
		pets.setStatus(PetStatus);
		pets.setTags(Arrays.asList(tag));
		pets.setCategory(category);
		
	    
	    Gson gson = new Gson();
        String jsonPayload = gson.toJson(pets);
        System.out.println("JSON Payload:");
        System.out.println(jsonPayload);
        
	    Response response = PetsEndPoints.addnewpet(pets);
	    response.then().log().all();
	    
	    Assert.assertEquals(response.statusCode(), 200);
	    
	    // Add additional assertions if needed
	}
	
	
	
	
	@Test (priority=2, dataProvider="PetID", dataProviderClass=DataProviders.class)
	public void getpetbyid(String PetID)
	{
		int petId = Integer.parseInt(PetID);
		
	 Response response= PetsEndPoints.getpetinfo(petId);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);
	  

	}
	
	
	//@Test (priority=3)
	public void getpetbystatus()
	{
		

	 Response response= PetsEndPoints.getpetsbystatus(this.pets.status);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	
	
	@Test (priority=4, dataProvider="PetID", dataProviderClass=DataProviders.class)
	public void deleteapet(String PetID)
	{
		
		int petId = Integer.parseInt(PetID);
		
	 Response response= PetsEndPoints.deletepet(petId);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	
	
	@Test (priority=5, dataProvider="PetID", dataProviderClass=DataProviders.class)
	public void updatepetwithformdata(String PetID)
	{
		int petId = Integer.parseInt(PetID);
		
		String status="sold";
		String name="bihari dog";
		
	 Response response= PetsEndPoints.updatepetdata(petId, status,name);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	
	
	@Test (priority=6, dataProvider="PetID", dataProviderClass=DataProviders.class)
	public void updatepetimage(String PetID)
	{
		int petId = Integer.parseInt(PetID);
		
		String filepath="/Users/gauravkumar/dog.jpg";
		
		
		
	 Response response= PetsEndPoints.uploadpetimage(filepath,petId);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	
	
	@Test (priority=7, dataProvider="PetID", dataProviderClass=DataProviders.class)

	public void updatepet(String PetID) {
		
		int petId = Integer.parseInt(PetID);

	    pets.setId(petId);
	    

	    faker=new Faker();
		
		
		category=new Category();
		category.setId(faker.hashCode());
		category.setName(faker.animal().name());
		
		tag =new Tag();
		tag.setId(faker.hashCode());
		tag.setName(faker.animal().name());
		
		pets.setName(faker.animal().name());
		pets.setPhotoUrls(new String[] {"www.updated-barton.org"});
		pets.setStatus("available");
		pets.setTags(Arrays.asList(tag));
		pets.setCategory(category);
	    
	    
	    System.out.println("updating existing pet with details:");
	    System.out.println("ID: " + pets.getId());
	    System.out.println("Pet Name: " + pets.getName());
	    System.out.println("Category ID: " + pets.getCategory().getId());
	    System.out.println("Category Name: " + pets.getCategory().getName());
	    System.out.println("Tag ID: " + pets.getTags().get(0));
	    System.out.println("status: " + pets.getStatus().toString());
	    System.out.println("PhotoURL: " + pets.getPhotoUrls().toString());
	    
	    
	    Gson gson = new Gson();
        String jsonPayload = gson.toJson(pets);
        System.out.println("JSON Payload:");
        System.out.println(jsonPayload);
        
	    Response response = PetsEndPoints.updatepet(pets);
	    response.then().log().all();
	    
	    Assert.assertEquals(response.statusCode(), 200);
	    
	}
	
}

