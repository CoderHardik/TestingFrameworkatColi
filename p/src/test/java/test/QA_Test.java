package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GlobalplayPage;
import pages.HomePage;
import pages.LoginPage;
import pages.NetworkPage;
import pages.PipelinePage;

public class QA_Test {

	WebDriver driver;
    LoginPage objLogin;
    HomePage objHomePage;
    NetworkPage objNetworkPage;
    PipelinePage objPipelinePage;
    GlobalplayPage objGlobalplayPage;
    String username = ReusableMethod.Username();
    String password = ReusableMethod.Password();
  

@BeforeMethod 
  public void beforeMethod() throws IOException {
  System.setProperty("webdriver.chrome.driver","/Users/hshah/Desktop/AutoFramework/ChromeWebDriver/chromedriver");
  driver = new ChromeDriver();
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  driver.get("https://intelligence-qa.ccmteam.com");
  }//end of beforeMethod

@Test(priority=0)
public void test_whats_happening_activities() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver,30);
	objLogin = new LoginPage(driver);
	objHomePage = new HomePage(driver); 
	objGlobalplayPage = new GlobalplayPage(driver);
	objLogin.setUserName(username);
	objLogin.setPassword(password);
	objLogin.clickLogin();
	
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getWhatshappeningSpinner()));
	wait.until(ExpectedConditions.elementToBeClickable(objHomePage.getWhats_happening_carousel_play()));
	objHomePage.Whats_happening_play_carousel_click();//click
	
	wait.until(whats_happening_carousel_play->"//a[@class=‘flex-active’]");
	wait.until(string->!objHomePage.Whats_happening_activities_count().contains("0"));
	wait.until(ExpectedConditions.visibilityOf(objHomePage.getwhats_happening_carousel_current_visible()));
	wait.until(whats_happening_carousel_current_visible->"//div[@class=‘wh-slide slide3’]");
	
	String whats_happening_play_count = objHomePage.Whats_happening_play_count();// extraction
	int whatshappening_play_count = Integer.parseInt(whats_happening_play_count);
	System.out.println("Number of open play in what's happening section is : "+whats_happening_play_count);
	
	wait.until(ExpectedConditions.elementToBeClickable(objHomePage.getWhats_happening_carousel_play_count()));
	objHomePage.Whats_happening_play_count_click();
	wait.until(string->!objGlobalplayPage.Played_by_yourTeam_Text().contains("0"));
	String globalplay_text = objGlobalplayPage.Played_by_yourTeam_Text().replaceAll("\\D+","");
	int play_count = Integer.parseInt(globalplay_text);
	
	System.out.println("Play count in Global play page for team is : "+play_count);
	Assert.assertEquals(whatshappening_play_count, play_count);
}//end of test


@AfterMethod
public void afterMethod() throws IOException {
    driver.quit();
} //end of afterMethod
	
	
}// end of class
