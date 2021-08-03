import allure.AllureListener;
import allure.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class PricingCalculatorPageTest extends BaseClass {
    public WebDriver driver;
    private static String query = "Google Cloud Platform Pricing Calculator";
    JavascriptExecutor executor;

    @BeforeMethod
    public void browserSetUp() {
        BaseClass baseClass = new BaseClass();
        driver = baseClass.initializeDriver();
        executor = (JavascriptExecutor) driver;
    }

    @Test(description = "Search Google Pricing Calculator, create Compute Engine and check estimated price")
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

    @Test(description = "Create indox using email generator, create Compute Engine, verify estimated price in email is equal estimated in form")
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
        tdriver.get().quit();
        if (tdriver.get() != null) {
            tdriver.remove();
        }
    }
}
