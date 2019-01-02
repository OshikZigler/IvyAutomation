package Tests;

import Screens.MainScreen;
import Utilities.Base;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainScreenTest extends Base {

    @BeforeClass
    public static void navigateToTargetURL() throws ParserConfigurationException, SAXException, IOException {
        browserSetUp();
        driver.get(getData("MainScreenURL"));
        //Initializing Webelements
        mainScreen = PageFactory.initElements(driver, MainScreen.class);
    }

    @Test
    public void validationTest() throws IOException, SAXException, ParserConfigurationException {

        //Clicking on Get Started button without inserting email or password
        mainScreen.SignUpValidationAction();
        commonMethods.verifyElementIsDisplayed(MainScreen.SignUpValidationErrorMessage);
        commonMethods.verifyElementContent(MainScreen.SignUpValidationErrorMessage, getData("SignUpValidation"));

        //Clicking on Get Started button with full name only
        mainScreen.SignUpValidationAction(getData("SignUpUserName"));
        commonMethods.verifyElementIsDisplayed(MainScreen.SignUpValidationErrorMessage);
        commonMethods.verifyElementContent(MainScreen.SignUpValidationErrorMessage, getData("SignUpValidation"));

        //Clicking on Get Started button with full name and phone number only
        mainScreen.SignUpValidationAction(getData("SignUpUserName"), getData("SignUpPhoneNumber"));
        commonMethods.verifyElementIsDisplayed(MainScreen.SignUpValidationErrorMessage);
        commonMethods.verifyElementContent(MainScreen.SignUpValidationErrorMessage, getData("SignUpValidation"));

    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();

    }
}
