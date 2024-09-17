import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParabankValidLoginTest {
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
    public void validLoginTest() {
        // Locate login fields
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));

        // Input registered credentials
        usernameField.sendKeys("testUser123");  // Using registered username
        passwordField.sendKeys("testPassword123");

        // Click the login button
        loginButton.click();

        // Verify successful login by checking for 'Accounts Overview' link
        WebElement accountOverviewLink;
        accountOverviewLink = driver.findElement(By.xpath("//a[contains(text(), 'Accounts Overview')]"));
        assert accountOverviewLink.isDisplayed() : "Login failed!";
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
