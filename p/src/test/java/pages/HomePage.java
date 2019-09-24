package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	

    @FindBy(xpath="//div[@class='ng-scope']")
    WebElement homePageNewsfeed;

    @FindBy(xpath="//div[@analytics-event='SCROLL to Header']")
    WebElement topLeftSection;
    
    @FindBy(xpath="//div[@class='col-xs-12 no-l-p no-r-p'] //span[@class='loader-wrap']")
    WebElement topLeftSectionSpinner;
    
    @FindBy(xpath="//div[@class='forecast-value-container ng-isolate-scope'] //span[@class='loader-wrap']")
    WebElement forecastSpinner;

	@FindBy(xpath="//div[@class=\"col-xs-12 what-hap-cont no-padding\"] //span[@class='loader-wrap']")
    WebElement whatshappeningSpinner;
	
	@FindBy(xpath="//div[@class='col-xs-12 no-padding'] //span[@class='loader-wrap']")
    WebElement top_deals_Spinner;

	@FindBy(xpath="//div[@class='col-xs-5 no-r-p p-sm-bottom link conn-cont']")
    WebElement topLeftSectionNetwork;
    
    @FindBy(xpath="//div[@class='col-xs-6 p-xxs-left p-sm-bottom link']")
    WebElement topLeftSectionOpportunity;
    
    @FindBy(xpath="//span[@class='forecast-highlight-revenue ng-binding ng-isolate-scope']")
    WebElement homepage_predictive_forecast;
    
    @FindBy(xpath="//div[@data-id='forecast-box'] //div[@class='valueLabelText']") 
    WebElement manage_predicitive_forecast;
    
	@FindBy(xpath="//button[@analytics-event='forecast:Month Time Filter selected']")
    WebElement monthly_predictive_forecast;
    
    @FindBy(xpath="//button[@analytics-event='forecast:Quarter Time Filter selected']") 
    WebElement quaterly_predictive_forecast;
    
    @FindBy(xpath="//button[@analytics-event='forecast:Year Time Filter selected']") 
    WebElement yearly_predictive_forecast;
    
    @FindBy(xpath="//ol[@class='flex-control-nav flex-control-paging']/child ::li[1] //a") 
    WebElement whats_happening_carousel_activities;
    
    @FindBy(xpath="//ol[@class='flex-control-nav flex-control-paging']/child ::li[2] //a") 
    WebElement whats_happening_carousel_risk;

	@FindBy(xpath="//ol[@class='flex-control-nav flex-control-paging']/child ::li[3] //a") 
    WebElement whats_happening_carousel_play;

	@FindBy(xpath="//li[@class='item-list ng-scope flex-active-slide']/child::div[1] //span[2]") // Since there are 2 posters I used parent and detecting it as child. Finding by itself was not possible
    WebElement whats_happening_carousel_activites_count;

	@FindBy(xpath="//li[@class='item-list ng-scope flex-active-slide']/child::div[1] //span[2]") // Same as above just banner change
    WebElement whats_happening_carousel_risk_count;
	
	@FindBy(xpath="//li[@class='item-list ng-scope flex-active-slide']") // Same as above just banner change
    WebElement whats_happening_carousel_risk_count_visible;
    
    @FindBy(xpath="//li[@class='item-list ng-scope flex-active-slide'] //span[2]") // Same as above just banner change
    WebElement whats_happening_carousel_play_count;
    
    @FindBy(xpath="//div[text()='Most Activity']")
    WebElement top_deals_most_activity_label_text;
    
    @FindBy(xpath="//div[text()='Closing Soon']")
    WebElement top_deals_closing_soon_label_text;
    
    @FindBy(xpath="//div[text()='Highest Revenue']")
    WebElement top_deals_highest_revenue_label_text;
    
    @FindBy(xpath="//li[@class='item-list ng-scope flex-active-slide']/child::div[1]") // SProblem with above xpath is that it will take whatever is active
    WebElement whats_happening_carousel_current_visible;// problem with this x path is that it has 2 elements with this class

    //create constructor. so 1) it can get loaded driver as parameter here.
	public HomePage(WebDriver driver){ 
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    
	    public String TopLeftSectionNetworkText(){
	    	return topLeftSectionNetwork.getText();
	    }
	    
	    public void TopLeftSectionNetworkClick(){
	    	 topLeftSectionNetwork.click();
	    }
	    
	    public String TopLeftSectionOpportunityText(){
	    	return topLeftSectionOpportunity.getText();
	    }
	    
	    public void TopLeftSectionOpportunityClick(){
	    	 topLeftSectionOpportunity.click();   // Here wait was not syncing up with .click so trying this
	    }
	    
	    public String Homepage_predicitive_forecast(){
	    	return homepage_predictive_forecast.getText();
	    }
	    
	    public void Homepage_predicitive_forecast_click() {
	    	homepage_predictive_forecast.click();
	    }
	    
	    public String Manage_predicitive_forecast_text(){
	    	return manage_predicitive_forecast.getText();
	    }
	    
	    public void Monthly_predicitive_forecast_click() {
	    	monthly_predictive_forecast.click();
	    }
	    
	    public void Quaterly_predicitive_forecast_click() {
	    	quaterly_predictive_forecast.click();
	    }
	    
	    public void Yearly_predicitive_forecast_click() {
	    	yearly_predictive_forecast.click();
	    }
	    
	    public void Whats_happening_activities_carousel_click() {
	    	whats_happening_carousel_activities.click();
	    }
	    
	    public void Whats_happening_risk_carousel_click() {
	    	whats_happening_carousel_risk.click();
	    }
	    
	    public void Whats_happening_play_carousel_click() {
	    	whats_happening_carousel_play.click();
	    }
	    
	    public String Whats_happening_activities_count() {
	    	return whats_happening_carousel_activites_count.getText();
	    }
	    
	    public void Whats_happening_activities_count_click() {
	    	whats_happening_carousel_activites_count.click();
	    }
	    
	    public String Whats_happening_risk_count() {
	    	return whats_happening_carousel_risk_count.getText();
	    }
	    
	    public void Whats_happening_risk_count_click() {
	    	whats_happening_carousel_risk_count.click();
	    }
	    
	    public WebElement getWhats_happening_carousel_activites_count() {
			return whats_happening_carousel_activites_count;
		}
	    
	    public String Whats_happening_play_count() {
	    	return whats_happening_carousel_play_count.getText();
	    }
	    
	    public void Whats_happening_play_count_click() {
	    	whats_happening_carousel_play_count.click();
	    }
	
		public WebElement getTopLeftSection() {
			return topLeftSection;
		}
	
		public WebElement getMonthly_predictive_forecast() {
			return monthly_predictive_forecast;
		}
	
		public WebElement getTopLeftSectionNetwork() {
			return topLeftSectionNetwork;
		}
		
		public WebElement getTopLeftSectionOpportunity() {
			return topLeftSectionOpportunity;
		}
		
		 public WebElement getManage_predicitive_forecast() {
			return manage_predicitive_forecast;
		}
	 
	    public WebElement getWhats_happening_carousel_activities() {
			return whats_happening_carousel_activities;
		}
	
		public WebElement getWhats_happening_carousel_risk() {
			return whats_happening_carousel_risk;
		}
		
		public String getWhats_happening_carousel_text_risk() {
			return whats_happening_carousel_risk.getText();
		}
	
		public WebElement getWhats_happening_carousel_play() {
			return whats_happening_carousel_play;
			}
		
		public WebElement getTopLeftSectionSpinner() {
			return topLeftSectionSpinner;
			}
		
		public WebElement getWhats_happening_carousel_play_count() {
			return whats_happening_carousel_play_count;
			}
		
		public String getWhats_happening_carousel_play_text_count() {
			return whats_happening_carousel_play_count.getText();
			}
		  
		public WebElement getWhatshappeningSpinner() {
			return whatshappeningSpinner;
			}
		  
		public WebElement getwhats_happening_carousel_current_visible() {
			return whats_happening_carousel_current_visible;
			}
		
		public WebElement getForecastSpinner() {
			return forecastSpinner;
			}
		
		public WebElement getTopDealSpinner() {
			return top_deals_Spinner;
		}
		
		public WebElement getTopDealHighestRevenueLabel() {
			return top_deals_highest_revenue_label_text;
		}
		
		public WebElement getTopDealClosingSoonLabel() {
			return top_deals_closing_soon_label_text;
		}
		public WebElement getTopDealMostActivityLabel() {
			return top_deals_most_activity_label_text;
		}
		
		
    }//end of class
    

