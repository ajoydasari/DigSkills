package pages;

import Utilities.Driver;
import cucumber.api.java.it.Ma;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConfirmVehicleDetails extends Driver{

    @FindBy(how = How.XPATH, using = ".//span[@class='reg-mark']")
    public WebElement registration;

    @FindBy(how = How.XPATH, using = ".//span[text()='Make']/..//strong")
    public WebElement make;

    @FindBy(how = How.XPATH, using = ".//span[text()='Colour']/..//strong")
    public WebElement colour;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Back')]")
    public WebElement back;


    public ConfirmVehicleDetails() {
            PageFactory.initElements(driver, this);
        }

        public void VerifyVehicleDetails(String Reg, String Make, String Colour)
        {
            logger.info("Validating the Vehicle details displayed : " + Reg + ", "+ Make + ", "+ Colour);

            //Retrieve the details displayed on the screen
            String regdisplayed = registration.getText();
            String makedisplayed = make.getText();
            String colourdisplayed = colour.getText();

            //Check that the registration number displayed correctly
            Assert.assertEquals(Reg, regdisplayed);
            Assert.assertEquals(Make, makedisplayed);
            Assert.assertEquals(Colour, colourdisplayed);
        }

        public void NavigateBack()
        {
            click(back);
        }
}
