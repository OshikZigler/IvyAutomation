package Utilities;

import Screens.MainScreen;
import Screens.MembersScreen;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;

    public static MembersScreen membersScreen;
    public static MainScreen mainScreen;
    public static CommonMethods commonMethods = new CommonMethods();




    public static void browserAdjusments() throws IOException, SAXException, ParserConfigurationException {
        System.setProperty("webdriver.chrome.driver", getData("ChromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //Initializing XML file
    public static String getData(String nodeName) throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File("/Users/oshikzigler/Automation/Ivy/src/Utilities/IvyConfig.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    public static void Reports () throws IOException, SAXException, ParserConfigurationException {
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(getData("ReportFilePath"));
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);


    }

    }

