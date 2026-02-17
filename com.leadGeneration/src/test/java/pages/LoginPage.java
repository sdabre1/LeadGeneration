package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators using stable attributes
    private By emailField = By.id("email"); // Using name as per standard login forms
    private By passwordField = By.id("password");
    private By loginBtn = By.xpath("//button[@data-testid='login-submit-btn']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Setting up explicit wait for synchronized element interaction
        this.wait = new WebDriverWait(driver, 15);
    }

    /**
     * Performs login operation with the provided credentials.
     * Uses explicit waits to ensure elements are interactable.
     * @param email - User email address
     * @param pass - User password
     */
    public void login(String email, String pass) {
        // Wait for the email field to be visible before starting interaction
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);

        // Enter password and click the sign-in button
        driver.findElement(passwordField).sendKeys(pass);

        // Ensure the login button is clickable before performing the action
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
}