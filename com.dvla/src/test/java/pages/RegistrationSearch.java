package pages;

import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegistrationSearch extends Driver {

    @FindBy(how = How.ID, using = "Vrm")
    public WebElement registrationEdit;

    @FindBy(how = How.NAME, using = "Continue")
    public WebElement continueBtn;

    public RegistrationSearch() {
        PageFactory.initElements(driver, this);
    }

    public void Search(String regNumber)
    {
        logger.info("Searching for Vehicle Registration Number : " + regNumber);
        sendKeys(registrationEdit, regNumber);
        click(continueBtn);
    }
}