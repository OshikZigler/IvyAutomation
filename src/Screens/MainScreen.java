package Screens;

import Utilities.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainScreen extends Base {

    public WebDriver driver;

    //Constructor
    public MainScreen(WebDriver localDriver){
        this.driver = localDriver;
    }

    @FindBy(how = How.ID, using = "nav-menu-item-7617")
    WebElement LoginButton;

    @FindBy(how = How.ID, using = "fullname-43")
    WebElement UserFullName;

    @FindBy(how = How.ID, using = "email-974")
    WebElement UserEmail;

    @FindBy(how = How.ID, using = "tel-909")
    WebElement UserPhoneNumber;

    @FindBy(how = How.ID, using = "ivysubmit-button")
    WebElement SubmitButton;

    @FindBy(how = How.ID, using = "professionalyes")
    WebElement IsProfessional;

    @FindBy(how = How.ID, using = "professionalno")
    WebElement IsNotProfessional;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Terms of Use')]")
    WebElement TermsOfUse;


    @FindBy(how = How.XPATH, using = "//div[@class='phone-not-valid email-not-valid']")
    public static WebElement SignUpValidationErrorMessage;


    public void loginAction(){
        LoginButton.click();
    }

    public void SignUpValidationAction(){
        SubmitButton.click();
    }

    public void SignUpValidationAction(String fullName) {
        UserFullName.sendKeys(fullName);
        SubmitButton.click();
    }

    public void SignUpValidationAction(String fullName , String phoneNumber) {
        UserFullName.sendKeys(fullName);
        UserPhoneNumber.sendKeys(phoneNumber);
        SubmitButton.click();
    }



}
