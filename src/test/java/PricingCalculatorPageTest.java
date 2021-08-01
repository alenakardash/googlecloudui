import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PricingCalculatorPageTest {
    private WebDriver driver;
    private static String query = "Google Cloud Platform Pricing Calculator";
    JavascriptExecutor executor;

    @BeforeMethod
    public void browserSetUp() {
        driver = new ChromeDriver();
        executor = (JavascriptExecutor) driver;
    }

    @Test
    public void checkEstimatedComputeEnginePrice() {
        ComputeEnginePage computeEnginePage = new HomePage(driver)
                .openPage()
                .getSearchResults(query)
                .navigateToQueryResult(query)
                .activateComputeEngineTab()
                .selectNumberOfInstance("4")
                .selectOSValue("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .selectMachineClass("Regular")
                .selectSeries("N1")
                .selectInstanceType("n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .changeAddGPUCheckBoxState()
                .setNumberOfGPUs("1")
                .setGPUType("NVIDIA Tesla V100")
                .setLocalSSD("2x375 GB")
                .setDataCenterLocation("Frankfurt")
                .setCommitedUsage("1 Year")
                .submitComputeEngineForm();

        String actualEstimatedPrice = computeEnginePage.getEstimatedPrice();

        Assert.assertTrue(actualEstimatedPrice.equals("USD 1,083.33"));
    }

    @Test
    public void checkEstimatedComputeEnginePriceFromEmail() {
        YopRandomAddressGeneratorPage yopRandomAddressGeneratorPage = new YopMailHomePage(driver)
                .openPage()
                .selectRandomAddressGenerateOption();

        String emailAddress = yopRandomAddressGeneratorPage.getGeneratedEmailAddress();

        YopInboxPage yopInboxPage = yopRandomAddressGeneratorPage.navigateToYopInbox();

        String inboxPageID = driver.getWindowHandle();

        executor.executeScript("window.open()");
        for (String windowHandle : driver.getWindowHandles()) {
            if ((!inboxPageID.contentEquals(windowHandle))) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        ComputeEnginePage computeEnginePage = new HomePage(driver)
                .openPage()
                .getSearchResults(query)
                .navigateToQueryResult(query)
                .activateComputeEngineTab()
                .selectNumberOfInstance("4")
                .selectOSValue("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .selectMachineClass("Regular")
                .selectSeries("N1")
                .selectInstanceType("n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .changeAddGPUCheckBoxState()
                .setNumberOfGPUs("1")
                .setGPUType("NVIDIA Tesla V100")
                .setLocalSSD("2x375 GB")
                .setDataCenterLocation("Frankfurt")
                .setCommitedUsage("1 Year")
                .submitComputeEngineForm()
                .submitEmailEstimate(emailAddress);

        String actualEstimatedPrice = computeEnginePage.getEstimatedPrice();

        driver.switchTo().window(inboxPageID);
        yopInboxPage.refreshInboxUntilEmailWithTextReceived("Google Cloud Platform Price Estimate");

        Assert.assertTrue(actualEstimatedPrice.equals(yopInboxPage.getEstimatedPriceFromLetter()), "Estimated price on Compute Engine Page and in Email does not match");
    }

    @AfterMethod
    public void browserTearDown() {
        driver.quit();
    }
}
