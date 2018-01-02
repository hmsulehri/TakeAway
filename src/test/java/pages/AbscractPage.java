package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.util.List;

public abstract class AbscractPage {

    protected final WebDriver driver;
    private WebDriverWait wait;

    protected static final int TIMEOUT_LONG = 30;
    protected static final int TIMEOUT_MEDIUM = 15;
    protected static final int TIMEOUT_SHORT = 5;

    public AbscractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementPresent(WebElement element, String attribute, String value) {
        try {
            element.getAttribute(attribute).equals(value);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean waitUntilElementVisible(WebElement element) {
        try {
            wait = new WebDriverWait(driver, TIMEOUT_SHORT);
            wait.until(ExpectedConditions.visibilityOf(element));
            Thread.sleep(500);
            return true;
        }
        catch (TimeoutException e) {
            return false;
        }
        catch (InterruptedException e){
            return false;
        }
    }

    protected boolean areElementsPresent(By by) {
        try {
            driver.findElements(by);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    protected boolean isElementPresent(WebElement element, By by) {
        try {
            element.findElements(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void pressEnterKey(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    public boolean waitUntilPageLoadsSuccessfully(String url) {

        try {
            wait = new WebDriverWait(driver, TIMEOUT_LONG);
            wait.until(ExpectedConditions.urlToBe(url));
            return true;
        }
        catch (TimeoutException e) {
            return false;
        }
    }

    public void navigateToBack(){
        driver.navigate().back();
    }

    public boolean clickOnDropDownValue(String element, String tagName, String value) {
        try
        {
            WebElement selectedOption = null;
            WebElement elementDropDown;
            List<WebElement> dropDownValues;

            elementDropDown  = driver.findElement(By.id(element));
            dropDownValues = elementDropDown.findElements(By.tagName(tagName));

            for(WebElement dropDown : dropDownValues) {
                if(dropDown.getText().equals(value)) {
                    selectedOption = dropDown;
                }
            }
            selectedOption.click();
            dropDownValues.clear();
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
