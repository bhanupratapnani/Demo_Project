package rough;


import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class pass {

	@Test
	public void testpass() 
	{
		Faker faker = new Faker();
		
		String randomPassword = Faker.instance().regexify("[A-Z]{1}[a-z]{1}[0-9]{1}[!@#$%^&*]{1}[A-Za-z0-9!@#$%^&*]{8}");
       
        System.out.println("Entered Random Password: " + randomPassword);
        
        String randomMobileNumber = "987" + faker.number().numberBetween(1000000, 9999999);
        
        System.out.println("Entered Random mobilenumber: " + randomMobileNumber);
	}
}
