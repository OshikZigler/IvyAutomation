package Tests;

import Screens.MembersScreen;
import Utilities.Base;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

@FixMethodOrder(MethodSorters.JVM)

public class MembersScreenTest extends Base {


    @BeforeClass
    public static void navigateToTargetURL() throws ParserConfigurationException, SAXException, IOException {

        browserAdjusments();
        driver.get(getData("MembersScreenURL"));

        //Initializing Webelements
        membersScreen = PageFactory.initElements(driver, MembersScreen.class);


    }

    @Test
    public void validationsTest() throws IOException, SAXException, ParserConfigurationException {

        //Clicking on Login button without inserting email or password
        membersScreen.SignInValidationAction();
        commonMethods.verifyElementIsDisplayed(MembersScreen.SignInValidationErrorMessage);
        commonMethods.verifyElementContent(MembersScreen.SignInValidationErrorMessage, getData("SignInValidation"));

        //Login with email and without password
        membersScreen.SignInValidationAction(getData("LoginUsername"));
        commonMethods.verifyElementIsDisplayed(MembersScreen.SignInValidationErrorMessage);
        commonMethods.verifyElementContent(MembersScreen.SignInValidationErrorMessage, getData("EmailPasswordValidation"));

        //Login with email and with wrong password
        membersScreen.SignInValidationAction(getData("LoginUsername"), getData("WrongPassword"));
        commonMethods.verifyElementIsDisplayed(MembersScreen.SignInValidationErrorMessage);
        commonMethods.verifyElementContent(MembersScreen.SignInValidationErrorMessage, getData("EmailPasswordValidation"));


    }

    @Test
    public void linksTest() throws IOException, SAXException, ParserConfigurationException {

        //Navigate to terms of use screen
        membersScreen.TermsOfUseAction();

        ArrayList<String> firstNewTab = new ArrayList<String>(driver.getWindowHandles());
        //Switch to new tab
        driver.switchTo().window(firstNewTab.get(1));
        //Check URL's
        String tosTabUrl = driver.getCurrentUrl();
        Assert.assertEquals("Wrong URL", getData("TermsOfUseUrl"), tosTabUrl);
        //Close new tab
        driver.close();

        //Switch to original tab
        driver.switchTo().window(firstNewTab.get(0));
        //Navigate to privacy policy screen
        membersScreen.PrivacyPolicyAction();

        ArrayList<String> secondNewTab = new ArrayList<String>(driver.getWindowHandles());
        //Switch to new tab
        driver.switchTo().window(secondNewTab.get(1));

        //Check URL's
        String privacyPolicyTabUrl = driver.getCurrentUrl();
        Assert.assertEquals("Wrong URL", getData("PrivacyPolicyUrl"), privacyPolicyTabUrl);
        //Close new tab
        driver.close();

        //Switch to original tab
        driver.switchTo().window(secondNewTab.get(0));

    }

    @Test
    public void loginInTest() throws IOException, SAXException, ParserConfigurationException {
        membersScreen.LogInAction(getData("LoginUsername"), getData("LoginPassword"));
        Assert.assertTrue("User is not signed in", MembersScreen.AvatarButton.isDisplayed());
    }


    @AfterClass
    public static void closeBrowser() {
        driver.quit();

    }


}
