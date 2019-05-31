package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.GlobalplayPage;
import pages.HomePage;
import pages.LoginPage;
//import pages.GlobalplayPage;
//import pages.HomePage;
//import pages.LoginPage;
//import pages.NetworkPage;
//import pages.PipelinePage;
import pages.NetworkPage;
import pages.PipelinePage;


/*
 * Test suite created by - Hardik Shah
 * Module - Homepage
 * Area covered - Top left section
 * This test is to be run as test ng
 */

public class LoginLoadTime {

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
    
  /* Author - Hardik Shah
   * Framework - Page Object Model
   * Object repository stored within properties file (@find by)
   * Use of impicit and explicit wait
   * 
   *   
 */
    

@Test(priority=0)
public void test_monthly_predictive_Forecast() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver,30);
	objLogin = new LoginPage(driver);
	objHomePage = new HomePage(driver); 
	objLogin.setUserName(username);
	objLogin.setPassword(password);
	objLogin.clickLogin();
	
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
	wait.until(ExpectedConditions.elementToBeClickable(objHomePage.getMonthly_predictive_forecast()));
	objHomePage.Monthly_predicitive_forecast_click();
	
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
	wait.until(string->!objHomePage.Homepage_predicitive_forecast().contains("$0"));
	String test_monthly_pred_forecast_home_page = objHomePage.Homepage_predicitive_forecast();
	System.out.println("Monthly predictive forecast on home page is : "+test_monthly_pred_forecast_home_page);
	objHomePage.Homepage_predicitive_forecast_click();
	
	wait.until(ExpectedConditions.textToBePresentInElement(objHomePage.getManage_predicitive_forecast(), test_monthly_pred_forecast_home_page));
	String test_monthly_pred_forecast_non_home_page = objHomePage.Manage_predicitive_forecast_text();
	System.out.println("Monthly predictive forecast on predicitive forecast page is : "+test_monthly_pred_forecast_non_home_page);
	Assert.assertEquals(test_monthly_pred_forecast_home_page, test_monthly_pred_forecast_non_home_page);
	}//End of test


@Test(priority=0)
	public void test_quaterly_predictive_Forecast() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,30);
		objLogin = new LoginPage(driver);
		objHomePage = new HomePage(driver); 
		objLogin.setUserName(username);
		objLogin.setPassword(password);
		objLogin.clickLogin();
		wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
		
		objHomePage.Quaterly_predicitive_forecast_click(); // This step to ensure that we have quaterly forecast in forecast section
		wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
		wait.until(string->!objHomePage.Homepage_predicitive_forecast().contains("$0"));
		String test_quaterly_pred_forecast_home_page = objHomePage.Homepage_predicitive_forecast();
		System.out.println("Quaterly predictive forecast on home page is : "+test_quaterly_pred_forecast_home_page);
		
		objHomePage.Homepage_predicitive_forecast_click();
		wait.until(ExpectedConditions.textToBePresentInElement(objHomePage.getManage_predicitive_forecast(), test_quaterly_pred_forecast_home_page));
		String test_quaterly_pred_forecast_non_home_page = objHomePage.Manage_predicitive_forecast_text();
		System.out.println("Quaterly predictive forecast on forecast page is : "+test_quaterly_pred_forecast_non_home_page);
		Assert.assertEquals(test_quaterly_pred_forecast_home_page, test_quaterly_pred_forecast_non_home_page);
}// End of test


@Test(priority=0)
	public void test_yearly_predictive_Forecast() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,30);
		objLogin = new LoginPage(driver);
		objHomePage = new HomePage(driver); 
		objLogin.setUserName(username);
		objLogin.setPassword(password);
		objLogin.clickLogin();
		wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
		objHomePage.Yearly_predicitive_forecast_click();
		
		wait.until(ExpectedConditions.invisibilityOf(objHomePage.getForecastSpinner()));
		wait.until(string->!objHomePage.Homepage_predicitive_forecast().contains("$0"));
		String test_yearly_pred_forecast_home_page = objHomePage.Homepage_predicitive_forecast();
		System.out.println("Yearly predictive forecast on home page is : "+test_yearly_pred_forecast_home_page);
		objHomePage.Homepage_predicitive_forecast_click();
		
		wait.until(ExpectedConditions.textToBePresentInElement(objHomePage.getManage_predicitive_forecast(), test_yearly_pred_forecast_home_page));
		String test_yearly_pred_forecast_non_home_page = objHomePage.Manage_predicitive_forecast_text();
		System.out.println("Yearly predictive forecast on forecast page is : "+test_yearly_pred_forecast_non_home_page);
		Assert.assertEquals(test_yearly_pred_forecast_home_page, test_yearly_pred_forecast_non_home_page);
}// End of test
   
@Test(priority=0)
public void test_topleft_network() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver,30);
	objLogin = new LoginPage(driver);
	objHomePage = new HomePage(driver); 
	objNetworkPage = new NetworkPage(driver);
	objLogin.setUserName(username);
	objLogin.setPassword(password);
	objLogin.clickLogin();
	
	wait.until(string->!objHomePage.TopLeftSectionNetworkText().contains("0"));
	String top_left_section_text = objHomePage.TopLeftSectionNetworkText().replaceAll("\\D+",""); // This will get "xx Connections" text from top left section
	System.out.println("Total connection shown in home page top left is : "+top_left_section_text);
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getTopLeftSectionSpinner()));
	objHomePage.TopLeftSectionNetworkClick();
	wait.until(string->!objNetworkPage.AllConnectionsText().contains("0"));
	String all_connection_text = objNetworkPage.AllConnectionsText().replaceAll("\\D+",""); // This replace all step will remove all stuff and will just keep number
	System.out.println("Total connection in network page are : "+all_connection_text);
	Assert.assertEquals(top_left_section_text, all_connection_text);
}// End of test

    
@Test(priority=0)
public void test_topleft_opportunity() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver,30);
	objLogin = new LoginPage(driver);
	objHomePage = new HomePage(driver); 
	objNetworkPage = new NetworkPage(driver);
	objPipelinePage = new PipelinePage(driver);
	objLogin.setUserName(username);
	objLogin.setPassword(password);
	objLogin.clickLogin();
	wait.until(string->!objHomePage.TopLeftSectionOpportunityText().contains("0"));
	String top_left_section_text = objHomePage.TopLeftSectionOpportunityText().replaceAll("\\D+",""); 
	System.out.println("Number of opportunity shown in home page top left section is : "+top_left_section_text);
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getTopLeftSectionSpinner()));
	objHomePage.TopLeftSectionOpportunityClick();
	wait.until(ExpectedConditions.textToBePresentInElement(objPipelinePage.getMyOpportunity(), top_left_section_text)); // Explicit wait to check if number is present in this element value
	String my_opportunity_text = objPipelinePage.MyopportunityText().replaceAll("\\D+",""); 
	System.out.println(my_opportunity_text);
	Assert.assertEquals(top_left_section_text, my_opportunity_text);
}// End of test
    
 

@Test(priority=0)
public void test_whats_happening_activities() throws InterruptedException{
	WebDriverWait wait = new WebDriverWait(driver,30);
	objLogin = new LoginPage(driver);
	objHomePage = new HomePage(driver); 
	objNetworkPage = new NetworkPage(driver);
	objPipelinePage = new PipelinePage(driver);
	objLogin.setUserName(username);
	objLogin.setPassword(password);
	objLogin.clickLogin();

	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getWhatshappeningSpinner()));
	wait.until(ExpectedConditions.elementToBeClickable(objHomePage.getWhats_happening_carousel_activities()));
	//objHomePage.Whats_happening_play_carousel_click();
	//wait.until(ExpectedConditions.visibilityOf(objHomePage.getwhats_happening_carousel_current_visible()));
	
	objHomePage.Whats_happening_activities_carousel_click();
	
	wait.until(whats_happening_carousel_activities->"//a[@class=‘flex-active’]");
	wait.until(string->!objHomePage.Whats_happening_activities_count().contains("0"));
	wait.until(ExpectedConditions.visibilityOf(objHomePage.getwhats_happening_carousel_current_visible()));
	wait.until(whats_happening_carousel_current_visible->"//div[@class=‘wh-slide slide1’]");
	
	String temp_whats_happening_activity_count = objHomePage.Whats_happening_activities_count();
	int Whats_happening_activities_count = Integer.parseInt(temp_whats_happening_activity_count);
	System.out.println("Activity count in what's happening section is : "+Whats_happening_activities_count);
	objHomePage.Whats_happening_activities_count_click(); // Change this click location to banner location add this object "whats_happening_carousel_activites_count"
	wait.until(string->!objPipelinePage.NewActivitiesText().contains("0"));
	
	String new_activities_text = objPipelinePage.NewActivitiesText().replaceAll("\\D+","");
	System.out.println("Opportunity count in which activity occured is : "+new_activities_text); // This is total opp count which will allow us to go through
	int opp_count = Integer.parseInt(new_activities_text); // Type casting - converting string to int
	int activities_pagescroll = opp_count/15;
	
	for (int i=0; i<=activities_pagescroll; i++) {
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    TimeUnit.SECONDS.sleep(5); 
	    }
	
	int final_activity_count = objPipelinePage.New_total_acitiviesText(opp_count);
	System.out.println("Total activity count: "+final_activity_count);
	Assert.assertEquals(Whats_happening_activities_count, final_activity_count);

}//end of test


@Test(priority=0)
public void test_whats_happening_risk() throws InterruptedException{
	//This case still has timing issues. Need to make sure to fix the carousel to get right value
	WebDriverWait wait = new WebDriverWait(driver,30);
	objLogin = new LoginPage(driver);
	objHomePage = new HomePage(driver); 
	objNetworkPage = new NetworkPage(driver);
	objPipelinePage = new PipelinePage(driver);
	objLogin.setUserName(username);
	objLogin.setPassword(password);
	objLogin.clickLogin();
	wait.until(ExpectedConditions.invisibilityOf(objHomePage.getWhatshappeningSpinner()));
	wait.until(ExpectedConditions.elementToBeClickable(objHomePage.getWhats_happening_carousel_risk()));
	
	objHomePage.Whats_happening_risk_carousel_click();
	
	wait.until(whats_happening_carousel_risk->"//a[@class=‘flex-active’]");
	wait.until(string->!objHomePage.Whats_happening_activities_count().contains("0"));
	wait.until(ExpectedConditions.visibilityOf(objHomePage.getwhats_happening_carousel_current_visible()));
	wait.until(whats_happening_carousel_current_visible->"//div[@class=‘wh-slide slide2’]");
	
	String whatshappening_risk_count = objHomePage.Whats_happening_risk_count();
	int Whats_happening_risk_count = Integer.parseInt(whatshappening_risk_count);
	System.out.println("Number of risk across all opportunity in what's happening section is : "+whatshappening_risk_count); //Currently RISK is not working in QA so not able to apply pipeline verification step
	wait.until(ExpectedConditions.elementToBeClickable(objHomePage.getWhats_happening_carousel_activites_count()));
	objHomePage.Whats_happening_risk_count_click();
	
	wait.until(string->!objPipelinePage.NewRisksText().contains("0"));
	String new_risk_text = objPipelinePage.NewRisksText().replaceAll("\\D+","");
	int opp_count = Integer.parseInt(new_risk_text); // Type casting - converting string to int
	System.out.println("Opportunity count in which risk is present in pipeline page is : "+opp_count);
	//((JavascriptExecutor) driver).executeScript("if((window.innerHeight+window.scrollY)>=document.body.offsetHeight){return true;}");
	int risk_pagescroll = opp_count/15; // This will tell how many times we need to scroll a page to get all opportunity
	for (int i=0; i<=risk_pagescroll; i++) {
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	TimeUnit.SECONDS.sleep(5); // need to fix this code as this will only paginate one time - need to have code for infinite scroll
	}
	
	int final_risk_count = objPipelinePage.New_total_risksText(opp_count);
	System.out.println("Total risk count: "+final_risk_count);
	Assert.assertEquals(Whats_happening_risk_count, final_risk_count);
}//end of test


  
   
@Test(priority=0)
public void test_whats_happening_play() throws InterruptedException{
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
    
/*
 * Tests to be worked on
 * Test 1 - Check connection in Top left section and compare with connection in network section - DONE
 * Test 2 - Check opportunity count in top left section and compare that with My opportunity count from pipeline section - DONE
 * Test 3 - Plan section - For each plan section, check that upon clicking it is going to appropriate pages (Check page title)- All 5 and for both SR and SM
 * Test 5 - Leaderboard - Numbers in leader board for quarterly are matching in predictive forecast 
 * Test 6 - Check for what's happening section that data is matching in pipeline section - (Hint - Just compare number in bracket in pipeline section - easy)
 * - Check that all above cases exits if home page is not loading
 * - Try to merge API call test for each of them, not sure how to do it for getAdasCall
 */
    

    @AfterMethod
	  public void afterMethod() throws IOException {
	      driver.quit();
	  } //end of afterMethod
	
}
