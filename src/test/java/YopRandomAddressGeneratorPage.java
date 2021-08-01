import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YopRandomAddressGeneratorPage extends AbstractPage {

    @FindBy(xpath = "//span[text()='Check Inbox']/parent::button")
    WebElement checkInbox;

    @FindBy(xpath = "//div[@id='egen']")
    WebElement generatedEmailAddressField;

    YopRandomAddressGeneratorPage(WebDriver driver) {
        super(driver);
    }

    public YopInboxPage navigateToYopInbox() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        checkInbox.click();
        return new YopInboxPage(driver);
    }

    public String getGeneratedEmailAddress() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='egen']")));
        return generatedEmailAddressField.getText();
    }
}
