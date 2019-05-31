package pages;


import java.util.List;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PipelinePage {
	WebDriver driver;
	
	
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //span[contains(text(),'My Opportunities')]")
	private 
    WebElement myOpportunity;
    
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //span[@class=‘fa fa-caret-down filter-dwn-arrow’]") 
    WebElement filterBy;

    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //ul[@class='dropdown-menu dropdown-filter-menu filter-menu']/child::li[1]") 
    WebElement myOpportunities_dropdownClick;
    
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //ul[@class='dropdown-menu dropdown-filter-menu filter-menu']/child::li[2]") 
    WebElement newActivites_dropdownClick;
    
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //ul[@class='dropdown-menu dropdown-filter-menu filter-menu']/child::li[3]") 
    WebElement newRisk_dropdownClick;
    
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //ul[@class='dropdown-menu dropdown-filter-menu filter-menu']/child::li[4]") 
    WebElement flagged_dropdownClick;
    
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //ul[@class='dropdown-menu dropdown-filter-menu filter-menu']/child::li[5]") 
    WebElement analyzing_dropdownClick;
    
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //span[contains(text(),'New Activities')]") 
    WebElement newActivities_text;
    
    @FindBy(xpath="//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 m-b pipeline-filter-container no-padding'] //span[contains(text(),'New Risks')]") 
    WebElement newRisks_text;
    
    @FindBy(xpath="//div[@class=‘card opportunity-card’]") // There will be multiple opportunity cards thus will have to create loop here 
    List<WebElement> opportunitycard_generic; 
    
    @FindBy(xpath="//div[@class=‘post-loading-container ng-scope’] //span[@class='loader-wrap']")  
    WebElement opportunitypage_spinner; 
    
    
    //@FindBy(xpath="//span[@ng-if=\"opp.new_activity_count>0 && filter === 'new_activities'\"]")  //This xpath was very hard to build. See here that I replaced single quote with back slash and double quote 
    //List<WebElement> activitycounts; 
    
   

	@FindBy(xpath="//div[@class='new-act-cont ng-binding ng-scope']")  
    List<WebElement> activitycounts; 
    
    @FindBy(xpath="//span[@ng-if=\"opp.new_risk_count>0 && filter === 'new_risks'\"]")  
    List<WebElement> riskcounts; 
    
    
    public PipelinePage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
        
    }
    
    public String MyopportunityText(){
    	return getMyOpportunity().getText();
    }
    
    public void Myopportunity_click() {
    	getMyOpportunity().click();
    }
    
    public void Filterby_dropdown_click() {
    	filterBy.click();
    }
    
    public void New_activities_click() {
    	newActivites_dropdownClick.click();
    }
    
    public void New_risk_click() {
    	newRisk_dropdownClick.click();
    }
    
    public void Flagged_click() {
    	flagged_dropdownClick.click();
    }
    
    public void Analyzing_click() {
    	analyzing_dropdownClick.click();
    }
    
    public void MyOpportunities_click() {
    	myOpportunities_dropdownClick.click();
    }
    
    public String NewActivitiesText(){
    	return newActivities_text.getText();
    }
    
    public String NewRisksText(){
    	return newRisks_text.getText();
    }
    
    public int New_total_acitiviesText(int total_opps){
    	int activity_count=0;
    	for (WebElement activitycount:activitycounts) {
    		String temp_activity_count = activitycount.getText().replaceAll("\\D+","");
    		activity_count = activity_count + Integer.parseInt(temp_activity_count);
    		
    	}
    	
    	return activity_count;
    }
    
    public int New_total_risksText(int total_opps){
    	int risk_count=0;
    	for (WebElement riskcount:riskcounts) {
    		String temp_risk_count = riskcount.getText().replaceAll("\\D+","");
    		risk_count = risk_count + Integer.parseInt(temp_risk_count);
    	}
    	
    	return risk_count;
    }

	public WebElement getMyOpportunity() {
		return myOpportunity;
	}


	
	 public WebElement getOpportunitypage_spinner() {
			return opportunitypage_spinner;
		}
    
}//end of class
