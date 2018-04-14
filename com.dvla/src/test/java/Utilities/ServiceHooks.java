package Utilities;

import org.apache.commons.io.FileUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceHooks extends Driver {

    @Before
    public void initializeTest()
    {
        //Initialise Chrome browser executable path and maximize window after opening
        logger.info("Chrome Driver Path : " + CHROMEDRIVER_PATH +"\n");
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        launchBrowser();
    }


    @After
    public void Screenshot(Scenario scenario) {

        //Step - Capture Screenshot only if the test fails
        if (scenario.isFailed()) {
            try {
                captureScreenshot(scenario.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Close Driver
        if(driver!=null)
            driver.close();

        //Quit Driver
        if(driver!=null)
            driver.quit();
    }

    //This method takes a picture of the current screen when called and saves in the Screenshots folder
    protected static void captureScreenshot(String testName) {
        try {
            String filePath = SCREENSHOT_FOLDER_PATH;
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date date = new Date();
            String date1 = dateFormat.format(date);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(filePath + "\\" + testName + "screenshot_" + date1 + ".png"));

            logger.info("***Placed screen shot in " + filePath + "\\screenshot_" + date1 + ".png" + " ***");
            //System.out.println("***Placed screen shot in " + filePath + "\\screenshot_" + date1 + ".png" + " ***");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}