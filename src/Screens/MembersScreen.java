package Screens;

import Utilities.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MembersScreen extends Base {

    public WebDriver driver;

    //Constructor
    public MembersScreen(WebDriver localDriver) {
        this.driver = localDriver;
    }

    @FindBy(how = How.ID, using = "user_email")
    WebElement UserEmail;

    @FindBy(how = How.ID, using = "user_password")
    WebElement UserPassword;

    @FindBy(how = How.XPATH, using = "//input[@value='Log in']")
    WebElement LoginButton;

    @FindBy(how = How.XPATH, using = "//a[@class='acts-sign-in pull-right']")
    WebElement ForgotPassword;

    @FindBy(how = How.ID, using = "user_remember_me")
    WebElement RememberMe;

    @FindBy(how = How.ID, using = "joinButton2")
    WebElement JoinButton;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Terms of Use')]")
    WebElement TermsOfUse;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Privacy Policy.')]")
    WebElement PrivacyPolicy;

    @FindBy(how = How.CLASS_NAME, using = "text-danger")
    public static WebElement SignInValidationErrorMessage;

    @FindBy(how = How.ID, using = "designer-avatar")
    public static WebElement AvatarButton;




    public void TermsOfUseAction() {
        TermsOfUse.click();
    }

    public void PrivacyPolicyAction() {
        PrivacyPolicy.click();
    }

    public void SignInValidationAction() {
        LoginButton.click();
    }

    public void SignInValidationAction(String email) {
        UserEmail.sendKeys(email);
        LoginButton.click();
    }

    public void SignInValidationAction(String email , String wrongPassword) {
        UserEmail.sendKeys(email);
        UserPassword.sendKeys(wrongPassword);
        LoginButton.click();
    }

    public void LogInAction(String email , String password) {
        UserEmail.sendKeys(email);
        UserPassword.sendKeys(password);
        LoginButton.click();
    }

    public void SignUpAction(){
        JoinButton.click();
    }

    public void RememberMeAction() {
        RememberMe.click();
    }

    public void ForgotPasswordAction(String email){
        ForgotPassword.click();
        UserEmail.sendKeys(email);
    }



}


