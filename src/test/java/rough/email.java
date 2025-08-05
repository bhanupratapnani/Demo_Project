package rough;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

public class email {
    public static void main(String[] args) throws InterruptedException {
    	
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        Faker faker = new Faker();
        String emailPrefix = faker.name().username().replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String email = emailPrefix + "@yopmail.com";
        
        System.out.println("Created email: "+email);

        try {
            driver.get("https://mojoauth.com/dashboard/signin");	

            driver.findElement(By.id("mojoauth-passwordless-email")).sendKeys(email);
            Thread.sleep(2000);
            driver.findElement(By.id("mojoauth-signin-button")).click();
            Thread.sleep(6000);
            
            ((JavascriptExecutor) driver).executeScript("window.open();");
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            
            driver.get("https://yopmail.com/en/");
            driver.findElement(By.xpath("//input[@id='login']")).sendKeys(emailPrefix+ "@yopmail.com");
            driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();

            Thread.sleep(5000); 

            driver.switchTo().frame("ifmail");

            String emailBody = driver.findElement(By.xpath("//body")).getText();
            String otp = emailBody.replaceAll("[^0-9]", "").substring(0, 6);
            System.out.println("Extracted OTP: " + otp);
            
            driver.switchTo().window(tabs.get(0));

            driver.findElement(By.id("pin0")).sendKeys(otp);
            driver.findElement(By.id("otp-submit-button")).click();
            Thread.sleep(3000);
            WebElement success = driver.findElement(By.xpath("//h1[text()='Create your first project']"));
            System.out.println("Verification: " + success.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(3000);
            driver.quit();
        }
    }

}
