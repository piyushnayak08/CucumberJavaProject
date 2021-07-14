package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Helper {
    Actions action;

    public void performMouseHover(WebElement element, WebDriver driver) {
        action = new Actions(driver);
        action.moveToElement(element).build().perform();
        ;
    }

    public void click(WebElement element) {
        element.click();
    }

    public boolean validateText(WebDriver driver, WebElement element, String expectedText) {
        String actualText = element.getText();
        if (actualText.equals(expectedText)) {
            return true;
        }
        return false;
    }

}
