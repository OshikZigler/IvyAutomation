package Tests;

import Screens.WelcomeScreen;
import Utilities.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
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

public class WelcomeScreenTest extends Utils {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    private static WelcomeScreen welcomeScreen;

    @BeforeClass
    public static void openBrowser() throws ParserConfigurationException, SAXException, IOException {
        System.setProperty("webdriver.chrome.driver", getData("ChromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(getData("URL"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Initializing Webelements
        welcomeScreen = PageFactory.initElements(driver, WelcomeScreen.class);

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

        ExtentHtmlReporter reporter = new ExtentHtmlReporter(getData("ReportFilePath"));
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        ExtentTest test = extent.createTest("Login validation", "Testing empty or wrong credentials");

        //Clicking on login buton without inserting email or password
        welcomeScreen.SignInValidationAction();
        test.log(Status.INFO, "Clicking on 'Login' without inserting username/password");
        WelcomeScreen.SignInValidationErrorMessage.isDisplayed();
        test.log(Status.INFO, "Error message is displayed");
        Assert.assertEquals("Validation error message is wrong", getData("SignInValidation"), WelcomeScreen.SignInValidationErrorMessage.getText());
        test.log(Status.INFO, "Error message syntax is correct");
        test.pass("PASSED");

        //Login with email and without password
        welcomeScreen.SignInValidationAction(getData("Username"));
        test.log(Status.INFO, "Clicking on 'Login' while inserting username without password");
        WelcomeScreen.SignInValidationErrorMessage.isDisplayed();
        test.log(Status.INFO, "Error message is displayed");
        Assert.assertEquals("Validation error message is wrong", getData("EmailPasswordValidation"), WelcomeScreen.SignInValidationErrorMessage.getText());
        test.log(Status.INFO, "Error message syntax is correct");
        test.pass("PASSED");

        //Login with email and with wrong password
        welcomeScreen.SignInValidationAction(getData("Username"), getData("WrongPassword"));
        test.log(Status.INFO, "Clicking on 'Login' while inserting username and wrong password");
        WelcomeScreen.SignInValidationErrorMessage.isDisplayed();
        test.log(Status.INFO, "Error message is displayed");
        Assert.assertEquals("Validation error message is wrong", getData("EmailPasswordValidation"), WelcomeScreen.SignInValidationErrorMessage.getText());
        test.log(Status.INFO, "Error message syntax is correct");
        test.pass("PASSED");

        //extent.flush();
    }

    @Test
    public void login() throws IOException, SAXException, ParserConfigurationException {

        //Navigate to terms of use screen
        welcomeScreen.TermsOfUseAction();

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


        driver.switchTo().window(firstNewTab.get(0));
        //Navigate to privacy policy screen
        welcomeScreen.PrivacyPolicyAction();

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

        driver.switchTo().window(secondNewTab.get(0));
        welcomeScreen.SignInAction(getData("Username"), getData("Password"));

    }


    @AfterClass
    public static void closeBrowser() {
        driver.quit();

    }


}
