package Utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver extends Config{
    protected static WebDriver driver = null;
    public static Logger logger = Logger.getLogger("DVLA");


    protected static void navigate(String URL) {
        logger.info("Navigating to URL: " + URL);
//        System.out.println("Navigating to URL: " + URL);
        driver.get(URL);
    }

    protected static void click(WebElement element) {
        logger.info("Clicking on Element: " + element.toString());
//        System.out.println("Clicking on Element: " + element.toString());
        element.click();
    }

    protected static void sendKeys(WebElement element, String text) {
        logger.info("Entering Text on Element: " + element.toString() + ", Text: "+ text);
//        System.out.println("Entering Text on Element: " + element.toString() + ", Text: "+ text);
        element.sendKeys(text);
    }

    public static void launchBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);

        //Maximize Chrome Browser Window after opening
        driver.manage().window().maximize();
    }
}
