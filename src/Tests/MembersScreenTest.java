package Tests;

import Screens.MainScreen;
import Screens.MembersScreen;
import Utilities.Utils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.JVM)

public class MembersScreenTest extends Utils {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    private static MainScreen mainScreen;
    private static MembersScreen membersScreen;

    @BeforeClass
    public static void openBrowser() throws ParserConfigurationException, SAXException, IOException {
        System.setProperty("webdriver.chrome.driver", getData("ChromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(getData("MembersScreenURL"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Initializing Webelements
        membersScreen = PageFactory.initElements(driver, MembersScreen.class);
        mainScreen = PageFactory.initElements(driver , MainScreen.class);

    }

    //Initializing XML file
    public static String getData(String nodeName) throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File("/Users/oshikzigler/Automation/Ivy/src/Resources/IvyConfig.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }


    @Test
    public void validationsTest() throws IOException, SAXException, ParserConfigurationException {

        //Clicking on Login button without inserting email or password
        membersScreen.SignInValidationAction();
        MembersScreen.SignInValidationErrorMessage.isDisplayed();
        Assert.assertEquals("Validation error message is wrong", getData("SignInValidation"), MembersScreen.SignInValidationErrorMessage.getText());

        //Login with email and without password
        membersScreen.SignInValidationAction(getData("Username"));
        MembersScreen.SignInValidationErrorMessage.isDisplayed();
        Assert.assertEquals("Validation error message is wrong", getData("EmailPasswordValidation"), MembersScreen.SignInValidationErrorMessage.getText());

        //Login with email and with wrong password
        membersScreen.SignInValidationAction(getData("Username"), getData("WrongPassword"));
        MembersScreen.SignInValidationErrorMessage.isDisplayed();
        Assert.assertEquals("Validation error message is wrong", getData("EmailPasswordValidation"), MembersScreen.SignInValidationErrorMessage.getText());
    }

    @Test
    public void linksTest() throws IOException, SAXException, ParserConfigurationException {

        //Navigate to terms of use screen
        membersScreen.TermsOfUseAction();

        ArrayList<String> firstNewTab = new ArrayList<String>(driver.getWindowHandles());
        //Switch to new tab
        driver.switchTo().window(firstNewTab.get(1));
        //Wait for element to be present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-menu-item-4632")));
        //Check URL's
        String tosTabUrl = driver.getCurrentUrl();
        Assert.assertEquals("Wrong tab", getData("TermsOfUseUrl"), tosTabUrl);
        //Close new tab
        driver.close();

        //Switch to original tab
        driver.switchTo().window(firstNewTab.get(0));
        //Navigate to privacy policy screen
        membersScreen.PrivacyPolicyAction();

        ArrayList<String> secondNewTab = new ArrayList<String>(driver.getWindowHandles());
        //Switch to new tab
        driver.switchTo().window(secondNewTab.get(1));

        //Wait for element to be present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-menu-item-4632")));
        //Check URL's
        String privacyPolicyTabUrl = driver.getCurrentUrl();
        Assert.assertEquals("Wrong tab", getData("PrivacyPolicyUrl"), privacyPolicyTabUrl);
        //Close new tab
        driver.close();

        //Switch to original tab
        driver.switchTo().window(secondNewTab.get(0));
        //membersScreen.LogInAction(getData("Username"), getData("Password"));

    }

    @Test
    public void loginInTest() throws IOException, SAXException, ParserConfigurationException {
        membersScreen.LogInAction(getData("Username") , getData("Password"));
        Assert.assertTrue("User is not signed in" , MembersScreen.AvatarButton.isDisplayed());
    }



    @AfterClass
    public static void closeBrowser() {
        driver.quit();

    }


}
