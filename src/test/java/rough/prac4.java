package rough;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class prac4 {
	
	@Test
    public void test1() throws Exception {
    	
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev.iamstillalive.com/login/");
        
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("edmundo.johnson@smail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Pass@123");
        driver.findElement(By.xpath("//button[@class='btn btn-gradient text-center fs-6 w-25 rounded-5 px-3 font-inter']")).click();
        driver.findElement(By.xpath("//img[@class='bird-img']")).click();
        
        

        String[] messages = {"Hello", "How are you?", "This is update", "File coming next", "Photo coming next"};
        String[] filePaths = {"lab1.pdf", "lab1.pdf", "lab3.pdf", "lab3.pdf", "lab3.pdf"};
        String[] photoPaths = {"A.jpg", "B.jpg", "C.png", "D.jpg", "E.png"};

	        PrintWriter log = new PrintWriter("SendLog.txt");

	  
	            // Send messages
	            for (String msg : messages) {
	                long start = System.currentTimeMillis();

	                WebElement messageBox = driver.findElement(By.id("replyBox")); // Change locator
	                messageBox.sendKeys(msg);
	                messageBox.sendKeys(Keys.ENTER);

	                long end = System.currentTimeMillis();
	                long duration = end - start;

	                log.println("Message: \"" + msg + "\" sent at " + timeStamp() + " | Time Taken: " + duration + " ms");
	                Thread.sleep(1000);
	            }

	            // Send files
	            for (String filePath : filePaths) {
	                long start = System.currentTimeMillis();

	                WebElement fileUpload = driver.findElement(By.xpath("//input[@type='file']")); // Change locator
	                fileUpload.sendKeys(new File(filePath).getAbsolutePath());
	                driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
	                
	                long end = System.currentTimeMillis();
	                long duration = end - start;

	                log.println("File: \"" + filePath + "\" sent at " + timeStamp() + " | Time Taken: " + duration + " ms");
	                Thread.sleep(2000);
	            }

	            // Send photos
	            for (String photoPath : photoPaths) {
	                long start = System.currentTimeMillis();

	                WebElement photoUpload = driver.findElement(By.xpath("//input[@type='file']")); // Change locator
	                photoUpload.sendKeys(new File(photoPath).getAbsolutePath());
	                driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
	                
	                long end = System.currentTimeMillis();
	                long duration = end - start;

	                log.println("Photo: \"" + photoPath + "\" sent at " + timeStamp() + " | Time Taken: " + duration + " ms");
	                Thread.sleep(2000);
	            }

	       
	        log.close();
	        driver.quit();
	    }

	    private static String timeStamp() {
	        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    }
	}

	

	
	


