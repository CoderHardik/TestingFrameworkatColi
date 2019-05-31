package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NetworkPage {

WebDriver driver;

    @FindBy(xpath="//div[@class='align-top-menu']/ul/li[2]")
    WebElement allConnections;

    public NetworkPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    
    public String AllConnectionsText(){
    	return allConnections.getText();
    }
    
}
