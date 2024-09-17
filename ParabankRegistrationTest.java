import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParabankRegistrationTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {

        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Parabank website
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    @Test
    public void registerNewUser() {
        // Fill in the registration form
        WebElement firstNameField = driver.findElement(By.id("customer.firstName"));
        WebElement lastNameField = driver.findElement(By.id("customer.lastName"));
        WebElement addressField = driver.findElement(By.id("customer.address.street"));
        WebElement cityField = driver.findElement(By.id("customer.address.city"));
        WebElement stateField = driver.findElement(By.id("customer.address.state"));
        WebElement zipCodeField = driver.findElement(By.id("customer.address.zipCode"));
        WebElement phoneField = driver.findElement(By.id("customer.phoneNumber"));
        WebElement ssnField = driver.findElement(By.id("customer.ssn"));
        WebElement usernameField = driver.findElement(By.id("customer.username"));
        WebElement passwordField = driver.findElement(By.id("customer.password"));
        WebElement confirmPasswordField = driver.findElement(By.id("repeatedPassword"));

        // Enter user information
        firstNameField.sendKeys("TestFirstName");
        lastNameField.sendKeys("TestLastName");
        addressField.sendKeys("123 Test Street");
        cityField.sendKeys("TestCity");
        stateField.sendKeys("TestState");
        zipCodeField.sendKeys("12345");
        phoneField.sendKeys("1234567890");
        ssnField.sendKeys("123-45-6789");
        usernameField.sendKeys("testUser123");  // Use a unique username
        passwordField.sendKeys("testPassword123");
        confirmPasswordField.sendKeys("testPassword123");

        // Click the Register button
        WebElement registerButton = driver.findElement(By.xpath("//input[@value='Register']"));
        registerButton.click();

        // Optional: Add a check to ensure registration was successful
        WebElement confirmationMessage = driver.findElement(By.xpath("//p[contains(text(), 'Your account was created successfully')]"));
        assert confirmationMessage.isDisplayed() : "Registration failed!";
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
