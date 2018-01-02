package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchPage;
import util.Data;

public class SearchTest extends AbstractTest{

    SearchPage searchPage() { return new SearchPage(driver); }

    @BeforeMethod
    @Override
    public void navigateTo() {
        if(!searchPage().isDisplayed())
            driver.navigate().to(Data.BASE_URL);
    }

   @Test(dataProvider = "validPostalCodes", dataProviderClass = Data.class, priority = 1)
    public void searchByPostalCode_ByPressingEnterKey(String postalCode, String searchedURL) {
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write postalCode in searchBar
        searchPage().writeText(postalCode);
        // Validate invalid input message is displayed in dropDown
        Assert.assertTrue(searchPage().validateDropDownMessage(Data.DROPDOWN_MESSAGE_INVALID_INPUT));
        // Press ENTER key
        searchPage().pressEnterInSearchBar();
        // Wait searchResultPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(searchedURL));
        // Validate searchResultPage is loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().equals(searchedURL));
        // Navigate to searchPage
        searchPage().navigateToBack();
    }

    @Test(dataProvider = "validPostalCodes", dataProviderClass = Data.class, priority = 2)
    public void searchByPostalCode_ByPressingSearchButton(String postalCode, String searchedURL) {
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write postalCode in searchBar
        searchPage().writeText(postalCode);
        // Validate invalid input message is displayed in dropDown
        Assert.assertTrue(searchPage().validateDropDownMessage(Data.DROPDOWN_MESSAGE_INVALID_INPUT));
        // Click searchButton
        searchPage().clickSearchButton();
        // Wait searchResultPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(searchedURL));
        // Validate searchResultPage is loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains(postalCode));
        // Navigate to searchPage
        searchPage().navigateToBack();
    }

    @Test(dataProvider = "nonExistentPostalCodes", dataProviderClass = Data.class, priority = 3)
    public void searchByNonExistentPostalCode(String postalCode, String desc) {
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write in searchBar and click searchButton
        searchPage().writeText(postalCode);
        searchPage().clickSearchButton();
        // Validate that non-existent delivery area ERROR displays
        Assert.assertTrue(searchPage().validateDeliveryAreaErrorDisplayed(Data.DELIVERY_AREA_ERROR_NONEXISTENT));
    }

   @Test(dataProvider = "invalidPostalCodes", dataProviderClass = Data.class, priority = 4)
    public void searchByInvalidPostalCode(String postalCode, String desc) {
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write in searchBar and click searchButton
        searchPage().writeText(postalCode);
        searchPage().clickSearchButton();
        // Validate that invalid delivery area ERROR displays
        Assert.assertTrue(searchPage().validateDeliveryAreaErrorDisplayed(Data.DELIVERY_AREA_ERROR_INVALID));
    }

    @Test(dataProvider = "streetAddress", dataProviderClass = Data.class, priority = 5)
    public void searchByWritingCompleteAddress_ByPressingEnterKey(String streetAddress, String streetNumber, String postalCode, String city, String searchedURL) {
        // Wait searchPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(Data.BASE_URL));
        // Validate that searchPage is loaded successfully
        Assert.assertEquals(driver.getCurrentUrl(), Data.BASE_URL);
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write in searchBar, wait for DropDown to appear and press ENTER key
        searchPage().writeText(streetAddress + ", " + postalCode + ", " + city);
        searchPage().waitUntilAutoCompleteDropDownDisplays();
        searchPage().pressEnterInSearchBar();
        // Wait searchResultPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(searchedURL));
        // Validate searchResultPage is loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains(postalCode));
        // Navigate to searchPage
        searchPage().navigateToBack();
    }

    @Test(dataProvider = "streetAddress", dataProviderClass = Data.class, priority = 6)
    public void searchByStreetOrPlace_BySelectingAutoCompleteDropDownValues(String streetName, String streetNumber, String postalCode, String city, String searchedURL) throws InterruptedException {
        // Wait for searchPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(Data.BASE_URL));
        // Validate that searchPage is loaded successfully
        Assert.assertEquals(driver.getCurrentUrl(), Data.BASE_URL);
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write in searchBar, wait for DropDown to appear and press ENTER key
        searchPage().writeText(streetName);
        searchPage().waitUntilAutoCompleteDropDownDisplays();
        // Select street address and city from dropDown
        searchPage().selectAutoCompleteDropDownValue(streetName + ", " + city);
        // Select complete address from dropDown
        searchPage().selectAutoCompleteDropDownValue(streetName + ", " + postalCode + " " + city);
        // Wait searchResultPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(searchedURL));
        // Validate searchResultPage is loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains(postalCode));
        // Navigate to searchPage
        searchPage().navigateToBack();
    }

    @Test(dataProvider = "streetAddress", dataProviderClass = Data.class, priority = 7)
    public void searchByPartialStreetOrPlaceName_BySelectingAutoCompleteDropDownValues(String streetName, String streetNumber,  String postalCode, String city, String searchedURL) throws InterruptedException {
        // Wait for searchPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(Data.BASE_URL));
        // Validate that searchPage is loaded successfully
        Assert.assertEquals(driver.getCurrentUrl(), Data.BASE_URL);
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write in searchBar, wait for DropDown to appear
        searchPage().writeText(streetName.substring(0, 7));
        searchPage().waitUntilAutoCompleteDropDownDisplays();
        // Select street address and city from dropDown
        searchPage().selectAutoCompleteDropDownValue(streetName + ", " + city);
        // Select complete address from dropDown
        searchPage().selectAutoCompleteDropDownValue(streetName + ", " + postalCode + " " + city);
        // Wait searchResultPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(searchedURL));
        // Validate searchResultPage is loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains(postalCode));
        // Navigate to searchPage
        searchPage().navigateToBack();
    }

    @Test(dataProvider = "streetNumber", dataProviderClass = Data.class, priority = 8)
    public void searchByStreetNumber_BySelectingAutoCompleteDropDownValues(String streetName, String streetNumber, String postalCode, String city, String searchedURL) throws InterruptedException {
        // Wait for searchPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(Data.BASE_URL));
        // Validate that searchPage is loaded successfully
        Assert.assertEquals(driver.getCurrentUrl(), Data.BASE_URL);
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write in searchBar, wait for DropDown to appear
        searchPage().writeText(streetNumber);
        // Select street address and city from dropDown
        searchPage().selectAutoCompleteDropDownValue(streetName + " " + streetNumber + ", " + city);
        // Wait searchResultPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(searchedURL));
        // Validate searchResultPage is loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains(postalCode));
        // Navigate to searchPage
        searchPage().navigateToBack();
    }

    @Test(dataProvider = "cityName", dataProviderClass = Data.class, priority = 9)
    public void searchByCity_BySelectingAutoCompleteDropDownValues(String streetName, String postalCode, String city, String searchedURL) throws InterruptedException {
        // Wait for searchPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(Data.BASE_URL));
        // Validate that searchPage is loaded successfully
        Assert.assertEquals(driver.getCurrentUrl(), Data.BASE_URL);
        // Validate searchPage is displayed
        Assert.assertTrue(searchPage().isDisplayed());
        // Write in searchBar, wait for DropDown to appear and press ENTER key
        searchPage().writeText(city);
        //searchPage().validateAutoCompleteDropDownDisplays();
        //searchPage().validateAutoCompleteDropDownValueDisplays(streetAddress + ", " + city);
        searchPage().selectAutoCompleteDropDownValue(streetName + ", " + city);
        //searchPage().validateAutoCompleteDropDownDisplays();
        //searchPage().pressEnterInSearchBar();
        // Wait searchResultPage to load successfully
        Assert.assertTrue(searchPage().waitUntilPageLoadsSuccessfully(searchedURL));
        // Validate searchResultPage is loaded successfully
        Assert.assertTrue(driver.getCurrentUrl().contains(postalCode));
        // Navigate to searchPage
        searchPage().navigateToBack();
    }
}
