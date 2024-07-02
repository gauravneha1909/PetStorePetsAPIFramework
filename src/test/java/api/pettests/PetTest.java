package api.pettests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;

import api.payload.Category;
import api.payload.Pets;
import api.payload.Tag;
import io.restassured.response.Response;
import api.endpoints.*;

public class PetTest {
	
	
	Faker faker;
	
	Category category;
	
	Pets pets;
	
	Tag tag;
	
	
	@BeforeClass
	public void beforetests()
	
	{
		
		faker=new Faker();
		
		
		category=new Category();
		category.setId(faker.hashCode());
		category.setName(faker.animal().name());
		
		tag =new Tag();
		tag.setId(faker.hashCode());
		tag.setName(faker.animal().name());
		
		pets = new Pets();
		pets.setId(faker.hashCode());
		pets.setName(faker.animal().name());
		pets.setPhotoUrls(new String[] {"www.cheree-barton.org"});
		pets.setStatus("available");
		pets.setTags(Arrays.asList(tag));
		pets.setCategory(category);

		

			
	}

	@Test

	public void addNewPet() {
	    System.out.println("Adding new pet with details:");
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
        
	    Response response = PetsEndPoints.addnewpet(pets);
	    response.then().log().all();
	    
	    Assert.assertEquals(response.statusCode(), 200);
	    
	    // Add additional assertions if needed
	}
	
	
	
	
	@Test (priority=2)
	public void getpetbyid()
	{
		
		
	 Response response= PetsEndPoints.getpetinfo(this.pets.getId());
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);
	  

	}
	
	
	@Test (priority=3)
	public void getpetbystatus()
	{
		
		
	 Response response= PetsEndPoints.getpetsbystatus(this.pets.status);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	
	
	@Test (priority=4)
	public void deleteapet()
	{
		
		
	 Response response= PetsEndPoints.deletepet(this.pets.id);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	
	
	@Test (priority=5)
	public void updatepetwithformdata()
	{
		
		String status="sold";
		String name="bihari dog";
		
	 Response response= PetsEndPoints.updatepetdata(this.pets.id, status,name);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	
	
	@Test (priority=6)
	public void updatepetimage()
	{
		String filepath="/Users/gauravkumar/dog.jpg";
		
		
		
	 Response response= PetsEndPoints.uploadpetimage(filepath,this.pets.id);
	 response.then().log().all();

	  Assert.assertEquals(response.statusCode(), 200);

	  
	  

	}
	/*@Test
    public void verifyIdAndNameInResponse() {
		
		 Response response= PetsEndPoints.getpetsbystatus(this.pets.status);
		 
        // Assume 'response' is the Response object containing your JSON response
        String jsonResponse = response.getBody().asString();

        // Using Gson to parse the JSON into a List of Pets objects
        Gson gson = new Gson();
        Pets[] petsArray = gson.fromJson(jsonResponse, Pets[].class);

        // Assert the id and name fields for each pet object
        for (Pets pet : petsArray) {
            Assert.assertNotNull(pet.getId(), "1827725498");
            Assert.assertNotNull(pet.getName(), "serval");
            // You can add more specific assertions as needed
        }*/
}

