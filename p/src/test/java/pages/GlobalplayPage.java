package pages;

import java.util.List;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GlobalplayPage {

WebDriver driver;
	
	
    @FindBy(xpath="//div[@class='row no-margins global-plays-main']/child::div[2] //button[3]") // need to change this xpath.. this is ready made from chrome
    WebElement playedByyourTeam;
    
    public GlobalplayPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
        
    }
    
    public String Played_by_yourTeam_Text(){
    	return playedByyourTeam.getText();
    }
    
    
    
    
}
