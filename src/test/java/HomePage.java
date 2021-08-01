import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage {
    public static final String HOMEPAGE_URL = "https://cloud.google.com";

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchField;

    HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public SearchResultsPage getSearchResults(String query) {
        searchField.click();
        searchField.sendKeys(query);
        searchField.sendKeys(Keys.ENTER);

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Search results for')]")));
        return searchResultsPage;
    }
}
