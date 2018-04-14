package pages;
import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GetVehicleInfo extends Driver {

    @FindBy(how = How.XPATH, using = ".//a[text()='Start now']")
    public WebElement startNow;

    public GetVehicleInfo() {
        PageFactory.initElements(driver, this);
    }

    public void StartNow()
    {
        navigate(APP_URL);
        logger.info("Clicking on Start Now button on DVLA for Vehicle Search ");
        click(startNow);
    }
}

