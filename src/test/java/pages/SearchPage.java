package pages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends AbscractPage {

    @FindBy(id = "imysearchstring")
    private WebElement searchBar;

    @FindBy(id = "submit_deliveryarea")
    private WebElement searchButton;

    @FindBy(id = "iautoCompleteDropDownContent")
    private WebElement autoCompleteDropDown;

    @FindBy(id = "ideliveryareaerror")
    private WebElement deliveryAreaError;

    @FindBy(className = "headline-xl-raised")
    private WebElement headline;

    @FindBy(id = "reference")
    private WebElement autoCompleteDropDownValue;

    @FindBy(className = "suggestions-location")
    private WebElement dropDownMessage;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isElementPresent(searchBar) && isElementPresent(searchButton);
    }

    public void writeText(String text) {
            clearText(searchBar);
            searchBar.sendKeys(text);
    }

    public void pressEnterInSearchBar() {
        searchBar.click();
        pressEnterKey(searchBar);
    }

    public void clearText(WebElement element) {
        try {
            element.click();
            Thread.sleep(1000);
            element.clear();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        catch (ElementNotVisibleException e) {
            System.out.println(e.getCause());
        }
    }

    public void clickSearchButton() {
        if (!(searchButton.isDisplayed())) {
            headline.click();
        }
        searchButton.click();
    }

    public boolean waitUntilAutoCompleteDropDownDisplays() {
        return waitUntilElementVisible(autoCompleteDropDown);
    }

    public boolean validateDeliveryAreaErrorDisplayed(String errorMsg) {
        return waitUntilElementVisible(deliveryAreaError) && deliveryAreaError.getText().equals(errorMsg);
    }

    public boolean validateAutoCompleteDropDownValueDisplays(String value){
        return isElementPresent(autoCompleteDropDownValue, "data-name", value);
    }

    public void selectAutoCompleteDropDownValue(String value)
    {
        if(waitUntilAutoCompleteDropDownDisplays())
            if(clickOnDropDownValue("iautoCompleteDropDownContent", "span", value));
    }

    public boolean validateDropDownMessage(String message) {
        Boolean flag = false;

        if(waitUntilElementVisible(dropDownMessage)) {
            String msg = dropDownMessage.findElement(By.tagName("span")).getText();

            if(msg.equals(message)) {
                flag = true;
            }
        }
        return flag;
    }
}
