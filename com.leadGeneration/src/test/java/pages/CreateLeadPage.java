package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateLeadPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators using stable data-testid attributes
    private By modalContainer = By.xpath("//div[@data-testid='modal-create-lead']");
    private By modalTitle = By.xpath("//h2[@data-testid='modal-create-lead-title']");
    private By nameField = By.xpath("//input[@data-testid='create-form-name-input']");
    private By emailField = By.xpath("//input[@data-testid='create-form-email-input']");
    private By companyField = By.xpath("//input[@data-testid='create-form-company-input']");
    private By dealValueField = By.xpath("//input[@data-testid='create-form-deal-value-input']");
    private By sourceDropdown = By.xpath("//button[@data-testid='create-form-source-select']");
    private By submitBtn = By.xpath("//button[@data-testid='create-form-submit-btn']");

    public CreateLeadPage(WebDriver driver) {
        this.driver = driver;
        // Initializing explicit wait with a 15-second timeout
        this.wait = new WebDriverWait(driver, 15);
    }

    /**
     * Fills lead details in the creation modal.
     * Handles dynamic Radix UI components and explicit waits.
     */
    public void fillLeadDetails(String name, String email, String company, String dealValue, String sourceName) {
        // Wait for the modal to be visible before interaction
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalContainer));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));

        // Interaction with basic input fields
        getElement(nameField).sendKeys(name);
        getElement(emailField).sendKeys(email);
        getElement(companyField).sendKeys(company);
        getElement(dealValueField).sendKeys(dealValue);

        // Interaction with custom Radix UI dropdown
        selectSource(sourceName);

        // Final submission
        clickSubmit();
    }

    /**
     * Selects an option from the custom Radix UI dropdown.
     * Note: Standard 'Select' class won't work here because the actual select is hidden.
     * @param sourceName - The text of the option to select (e.g., "LinkedIn", "Website")
     */
    public void selectSource(String sourceName) {
        // 1. Click the dropdown trigger button first
        By sourceDropdownTrigger = By.xpath("//button[@data-testid='create-form-source-select']");
        wait.until(ExpectedConditions.elementToBeClickable(sourceDropdownTrigger)).click();

        // 2. Wait for the options to pop up and click the specific one by text
        // Using a dynamic XPath to find the span or div containing the source name
        By optionLocator = By.xpath("//div[@role='option']//span[text()='" + sourceName + "'] | //span[text()='" + sourceName + "']");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
        } catch (Exception e) {
            // Fallback: If standard click is intercepted, use JavaScript Click
            WebElement option = driver.findElement(optionLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
    }

    private void clickSubmit() {
        WebElement btn = getElement(submitBtn);
        try {
            btn.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if the element is intercepted by an overlay
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    private WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}