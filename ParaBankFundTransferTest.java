import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ParaBankFundTransferTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    public void transferFundsTest() {
        // Log in first
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        usernameField.sendKeys("testUser123");
        passwordField.sendKeys("testPassword123");
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));
        loginButton.click();

        // Wait for the page to load and verify successful login by checking for "Accounts Overview"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement accountOverview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Accounts Overview")));
        Assert.assertTrue(accountOverview.isDisplayed(), "Login was not successful.");

        // Navigate to Transfer Funds page
        WebElement transferFundsLink = driver.findElement(By.linkText("Transfer Funds"));
        transferFundsLink.click();

        // Verify the Transfer Funds page is loaded
        WebElement transferPageHeader = driver.findElement(By.xpath("//h1[contains(text(),'Transfer Funds')]"));
        Assert.assertTrue(transferPageHeader.isDisplayed(), "Transfer Funds page not loaded.");

        // Fill transfer form
        WebElement amountField = driver.findElement(By.id("amount"));
        WebElement fromAccountDropdown = driver.findElement(By.id("fromAccountId"));
        WebElement toAccountDropdown = driver.findElement(By.id("toAccountId"));
        amountField.sendKeys("100");

        // Use Select to choose accounts (replace with actual account numbers)
        fromAccountDropdown.sendKeys("12345");
        toAccountDropdown.sendKeys("67890");

        // Submit transfer
        WebElement transferButton = driver.findElement(By.xpath("//input[@value='Transfer']"));
        transferButton.click();

        // Wait for success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Transfer Complete')]")));
        Assert.assertTrue(successMessage.isDisplayed(), "Transfer was not successful.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
