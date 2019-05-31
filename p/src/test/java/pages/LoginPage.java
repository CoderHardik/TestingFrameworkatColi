package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//ref : https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html


public class LoginPage {

	WebDriver driver;

    @FindBy(xpath="//input[@id='email_input']")
    WebElement username;

    @FindBy(xpath="//input[@id='password']")
    WebElement password;
    
    @FindBy(xpath="//button[@type='submit']")
    WebElement login;

    
     public LoginPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    
    
    public void setUserName(String strUserName){
        username.sendKeys(strUserName);
    }
    
    public void setPassword(String strPassword){
        password.sendKeys(strPassword);
    }
 
    
    public void clickLogin(){
        login.click();
}
    
    
}
