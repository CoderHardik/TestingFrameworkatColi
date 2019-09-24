package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jdk.internal.jline.internal.Log;
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
    //String username = ReusableMethod.Username();
    //String password = ReusableMethod.Password();
    private static Logger Log=  LogManager.getLogger(LoginLoadTimeTest.class.getName());

@BeforeMethod 
  public void beforeMethod() throws IOException {
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/resources/ChromeWebDriver/chromedriver");
	  //System.setProperty("webdriver.chrome.driver","/Users/hshah/Desktop/AutoFramework/ChromeWebDriver/chromedriver");
	  Properties prop = new Properties();
	  FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/resources/data.properties");
	  //FileInputStream fis = new FileInputStream("/Users/hshah/git/GUIandAPItestColi/p/src/test/java/resources/data.properties");
	  
	  /* Following code for headless option
	  ChromeOptions options = new ChromeOptions();
	  options.addArguments("headless");
	  driver = new ChromeDriver(options);
	  */
	  prop.load(fis);
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get(prop.getProperty("url"));
	  objLogin = new LoginPage(driver);
	  objHomePage = new HomePage(driver); 
	  objNetworkPage = new NetworkPage(driver);
	  objPipelinePage = new PipelinePage(driver);
	  objLogin.setUserName(prop.getProperty("username"));  
	  Log.debug("Entered user name");
	  objLogin.setPassword(prop.getProperty("password"));	
	  Log.debug("Entered password");
	  objLogin.clickLogin();
	  Log.debug("Click login button");
	  
  }//end of beforeMethod

@Test(priority=0)
public void test_monthly_predictive_Forecast() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver,30);
	
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
	wait.until(ExpectedConditions.elementToBeClickable(objHomePage.getMonthly_predictive_forecast()));
	objHomePage.Monthly_predicitive_forecast_click();
	Log.debug("Click Monthly predictive forecast on home page");
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
	wait.until(string->!objHomePage.Homepage_predicitive_forecast().contains("$0"));
	String test_monthly_pred_forecast_home_page = objHomePage.Homepage_predicitive_forecast();
	Log.info("Monthly predictive forecast on home page is : "+test_monthly_pred_forecast_home_page);
	System.out.println("Monthly predictive forecast on home page is : "+test_monthly_pred_forecast_home_page);
	objHomePage.Homepage_predicitive_forecast_click();
	
	wait.until(ExpectedConditions.textToBePresentInElement(objHomePage.getManage_predicitive_forecast(), test_monthly_pred_forecast_home_page));
	String test_monthly_pred_forecast_non_home_page = objHomePage.Manage_predicitive_forecast_text();
	System.out.println("Monthly predictive forecast on predicitive forecast page is : "+test_monthly_pred_forecast_non_home_page);
	Assert.assertEquals(test_monthly_pred_forecast_home_page, test_monthly_pred_forecast_non_home_page);
}//End of test


@AfterMethod
public void afterMethod() throws IOException {
    driver.quit();
} //end of afterMethod
	
	
}// end of class
