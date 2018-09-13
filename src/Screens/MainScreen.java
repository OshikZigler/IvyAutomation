package Screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainScreen {

    WebDriver driver;

    //Constructor
    public MainScreen(WebDriver localDriver){
        this.driver = localDriver;
    }

    @FindBy(how = How.ID, using = "nav-menu-item-7617")
    WebElement LoginButton;

    @FindBy(how = How.ID, using = "fullname-43")
    WebElement FullName;

    @FindBy(how = How.ID, using = "email-974")
    WebElement UserEmail;

    @FindBy(how = How.ID, using = "tel-909")
    WebElement UserPhoneNumber;

    @FindBy(how = How.ID, using = "ivysubmit-button")
    WebElement SubmitButton;


    public void loginAction(){
        LoginButton.click();
    }

}
