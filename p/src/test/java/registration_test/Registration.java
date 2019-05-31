package registration_test;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Registration {

	WebDriver driver;
    
    @BeforeMethod 
	  public void beforeMethod() throws IOException {
		
		System.setProperty("webdriver.chrome.driver","/Users/hshah/Desktop/AutoFramework/ChromeWebDriver/chromedriver");
		  driver = new ChromeDriver();
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      driver.get("https://intelligence-qa.ccmteam.com/external-user-invitation/welcome/5b6891839a92cd8c37b73dcf"); 
	  }//end of beforeMethod
    
    
    @Test(priority=0)
    
    public void test_registration() throws InterruptedException {
    	
//Registration terms page
    			//driver.findElement(By.id("roundedOne")).click();
         	driver.findElement(By.xpath("//img[@class='int-image']"));
         	driver.findElement(By.xpath("//span[@class='link terms-link']"));
         	Thread.sleep(2000);
         		//driver.findElement(By.xpath("//input[@class='ng-untouched ng-dirty ng-valid-parse ng-empty ng-invalid ng-invalid-required']"));
         		//driver.findElement(By.xpath("//input[@id='roundedOne']")).click();
         	driver.findElement(By.xpath("//label[@for='roundedOne']")).click();
    	    driver.findElement(By.xpath("//button[@type='submit']")).click();
    	
    	
 //Step 1 page
    	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test123#");
    	    driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("Test123#");
    	    driver.findElement(By.xpath("//button[@type='submit']")).click();
    	    
 //Step 2 page
    	    Thread.sleep(2000);
    	    driver.findElement(By.xpath("//button[@type='submit']")).click();
    	    
// Step -3 Final
    	    Thread.sleep(2000);
    	    WebElement quote = driver.findElement(By.xpath("//button[@type='submit']"));
    	    Assert.assertEquals(true, quote.isDisplayed());
    	    
    	    
    	
    }// End of test
    /*
    @AfterMethod
	  public void afterMethod() throws IOException {
	      driver.quit();
	      
	  } //end of afterMethod
	*/
}
