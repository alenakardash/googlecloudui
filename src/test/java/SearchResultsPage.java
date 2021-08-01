import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends AbstractPage {

    SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public PricingCalculatorPage navigateToQueryResult(String query) {
        String searchResultLink = String.format("//b[contains(text(), '%s')]", query);

        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResultLink)));

        driver.findElement(By.xpath(searchResultLink)).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return new PricingCalculatorPage(driver);
    }
}