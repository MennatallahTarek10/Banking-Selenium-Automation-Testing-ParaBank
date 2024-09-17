import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ParabankInvalidLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {

        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Parabank website
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    public void invalidLoginTest() {
        // Locate login fields
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));

        // Input invalid credentials
        usernameField.sendKeys("invalidUser");
        passwordField.sendKeys("wrongPassword");

        // Click the login button
        loginButton.click();

        // Verify that the login failed by checking for error message
        WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(), 'The username and password could not be verified.')]"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed!");
         }


    @AfterMethod
    public void tearDown() {
        // Close the browser
       // driver.quit();
    }
}
