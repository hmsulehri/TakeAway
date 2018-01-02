package tests;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import selenium.SeleniumConfig;

public abstract class AbstractTest {
    protected WebDriver driver;
    private SeleniumConfig seleniumConfig;

    @Parameters("browserName")
    @BeforeClass
    public void setup(String browser) throws Exception{
        seleniumConfig = new SeleniumConfig();

        try {
            driver = seleniumConfig.build(browser);
        }
        catch (NoSuchSessionException e) {
            System.out.println(e.getCause());
        }
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }

    @BeforeMethod
    public abstract void navigateTo();
}
