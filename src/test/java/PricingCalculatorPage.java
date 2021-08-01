import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricingCalculatorPage extends AbstractPage {
    public static final String PRICING_CALCULATOR_PAGE_URL = "https://cloud.google.com/products/calculator";

    @FindBy(xpath = "//md-tab-item//div[text()=\'Compute Engine\']")
    WebElement computeEngineTabItem;

    PricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public ComputeEnginePage activateComputeEngineTab() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        driver.switchTo().frame(0);

        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("myFrame"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//md-tab-item//div[text()='Compute Engine']")));

        computeEngineTabItem.click();
        return new ComputeEnginePage(driver);
    }
}
