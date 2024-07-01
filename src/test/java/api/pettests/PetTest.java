package api.pettests;

import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import api.payload.Category;
import api.payload.Pets;
import api.payload.Tag;

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
		
		pets = new Pets();
		
		tag =new Tag();
		
		
		
		category.setId(faker.hashCode());
		category.setName(faker.animal().name());
		pets.setId(faker.hashCode());
		pets.setName(faker.animal().name());
		pets.setPhotoUrls(faker.internet().image());
		pets.setStatus("available");
		tag.setId(faker.hashCode());
		tag.setName(faker.animal().name());
		
		
		
		
		
		
		
		
		
	}

	
	public void getpetbyid()
	
	{
		
		PetsEndPoints.getpetinfo(0);
		
		
	}
}
