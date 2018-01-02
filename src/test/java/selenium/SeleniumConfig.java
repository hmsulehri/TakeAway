package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import util.Data;

public class SeleniumConfig {

    private URL endpoint;
    private WebDriver driver;

    public WebDriver build(String browser) throws Exception{
        try {
            if(browser.equals("Chrome")) {
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
                driver = new ChromeDriver();
            }
            endpoint = new URL(Data.BASE_URL);

            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().window().fullscreen();
            driver.navigate().to(endpoint);

            if(!(isURLAccessible(endpoint).equals("OK"))) {
                throw new UnknownHostException();
            }
        }
        catch (MalformedURLException e) {
            System.out.println(e.getCause());
        }
        catch(UnknownHostException e) {
            System.out.println(e.getCause());
        }

        return driver;
    }

    public static String isURLAccessible(URL url) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.connect();
            String response = connection.getResponseMessage();
            connection.disconnect();

            return response;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
