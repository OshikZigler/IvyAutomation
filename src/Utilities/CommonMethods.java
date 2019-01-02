package Utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.fail;

public class CommonMethods extends Base {


    public void verifyElementIsDisplayed(WebElement element) {

        try {
            Assert.assertTrue("Element is not displayed", element.isDisplayed());
        } catch (Exception e) {
            fail("Element doesn't exist," + e.getMessage());
        }
    }

    public void verifyElementContent(WebElement element, String expectedValue) {

        try {
            String actualValue = element.getText();
            Assert.assertEquals("Element content is wrong", expectedValue, actualValue);
        } catch (Exception e) {
            fail("Element doesn't exist");
        }
    }


}



