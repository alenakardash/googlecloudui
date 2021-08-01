import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputeEnginePage extends AbstractPage {

    @FindBy(xpath = "//input[@name = 'quantity' and @ng-model='listingCtrl.computeServer.quantity']")
    WebElement numberOfInstancesField;

    @FindBy(xpath = "//*[text()='Operating System / Software']/parent::md-input-container")
    WebElement operatingSystemDropdown;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    WebElement machineClassField;

    @FindBy(xpath = "//md-select[@name='series']")
    WebElement seriesDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    WebElement machineTypeDropdown;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//md-checkbox[@aria-label='Add GPUs']")
    WebElement addGPUCheckbox;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    WebElement numberOfGPUsDropdown;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    WebElement GPUTypeDropdown;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//md-select[@placeholder='Local SSD']")
    WebElement localSSDDropdown;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//md-select[@placeholder='Datacenter location']")
    WebElement dataCenterLocationDropdown;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//md-select[@placeholder='Committed usage']")
    WebElement committedUsageDropdown;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[@aria-label='Add to Estimate']")
    WebElement addToEstimateButton;

    @FindBy(xpath = "//button[@aria-label='Email Estimate']")
    WebElement emailEstimateButton;

    @FindBy(xpath = "//form[@name='emailForm']//label[contains(text(), 'Email')]/following-sibling::input")
    WebElement emailAddressField;

    @FindBy(xpath = "//form[@name='emailForm']//button[contains(text(), 'Send Email')]")
    WebElement sendEmailButton;

    @FindBy(xpath = "//h2[@class='md-title']/b")
    WebElement estimatedPriceText;

    ComputeEnginePage(WebDriver driver) {
        super(driver);
    }

    public ComputeEnginePage selectNumberOfInstance(String numberOfInstances) {
        numberOfInstancesField.sendKeys(numberOfInstances);
        return this;
    }

    public ComputeEnginePage selectOSValue(String OSValue) {
        String OSValueXpath = String.format("//div[@class='md-text' and contains(text(), '%s')]/ancestor::md-select-value", OSValue);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", operatingSystemDropdown);

        driver.findElement(By.xpath(OSValueXpath)).click();
        return this;
    }

    public ComputeEnginePage selectMachineClass(String machineClassType) {
        String machineClassTypeXpath = String.format("//md-select[@placeholder='VM Class']//div[@class='md-text' and text()='%s']", machineClassType);

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].scrollIntoView();", machineClassField);
        executor.executeScript("arguments[0].click();", machineClassField);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(machineClassTypeXpath)));
        return this;
    }

    public ComputeEnginePage selectSeries(String series) {
        String seriesXpath = String.format("//div[contains(text(), '%s')]", series);

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].click();", seriesDropdown);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(seriesXpath)));
        return this;
    }

    public ComputeEnginePage selectInstanceType(String instanceType) {
        String instanceTypeXpath = String.format("//md-option/div[contains(text(), '%s')]", instanceType);

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].click();", machineTypeDropdown);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(instanceTypeXpath)));
        return this;
    }

    public ComputeEnginePage changeAddGPUCheckBoxState() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].scrollIntoView();", addGPUCheckbox);
        executor.executeScript("arguments[0].click();", addGPUCheckbox);
        return this;
    }

    public ComputeEnginePage setNumberOfGPUs(String GPUnumber) {
        String fullXPath = String.format("//md-option[@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]' and @value='%s']", GPUnumber);

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//md-select[@placeholder='Number of GPUs']")));

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].click();", numberOfGPUsDropdown);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(fullXPath)));
        return this;
    }

    public ComputeEnginePage setGPUType(String GPUType) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", GPUTypeDropdown);
        String GPUTypeXpath = String.format("//div[contains(text(),'%s')]", GPUType);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(GPUTypeXpath)));
        return this;
    }

    public ComputeEnginePage setLocalSSD(String localSSD) {
        String localSSDXpath = String.format("//div[contains(text(), '%s')]", localSSD);

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].click();", localSSDDropdown);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(localSSDXpath)));
        return this;
    }

    public ComputeEnginePage setDataCenterLocation(String country) {
        String countryXPath = String.format("//div[contains(text(), '%s')]", country);

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].scrollIntoView();", dataCenterLocationDropdown);
        executor.executeScript("arguments[0].click();", dataCenterLocationDropdown);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(countryXPath)));
        return this;
    }

    public ComputeEnginePage setCommitedUsage(String commitedUsage) {
        String commitedUsageXPath = String.format("//div[text()='%s']", commitedUsage);

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].scrollIntoView();", committedUsageDropdown);
        executor.executeScript("arguments[0].click();", committedUsageDropdown);
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(commitedUsageXPath)));
        return this;
    }

    public ComputeEnginePage submitComputeEngineForm() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@name='ComputeEngineForm']//button[@aria-label='Add to Estimate']")));

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", addToEstimateButton);
        executor.executeScript("arguments[0].click();", addToEstimateButton);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Total Estimated Cost:')]")));
        return this;
    }

    public ComputeEnginePage submitEmailEstimate(String emailAddressToSend) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("arguments[0].scrollIntoView();", emailEstimateButton);
        executor.executeScript("arguments[0].click();", emailEstimateButton);

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@name='emailForm']//label[contains(text(), 'Email')]")));

        emailAddressField.sendKeys(emailAddressToSend);
        executor.executeScript("arguments[0].scrollIntoView();", sendEmailButton);
        executor.executeScript("arguments[0].click();", sendEmailButton);
        return this;
    }

    public String getEstimatedPrice() {
        Pattern pattern = Pattern.compile("USD(\\s[\\d,.]+)");
        Matcher matcher = pattern.matcher(estimatedPriceText.getText());
        matcher.find();
        return matcher.group();
    }
}
