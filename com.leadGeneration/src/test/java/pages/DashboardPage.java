package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators using stable data-testid and specific text matches
    private By createLeadBtn = By.xpath("//button[@data-testid='leads-create-new-btn']");
    private By successToastTitle = By.xpath("//div[text()='Success']");
    private By successToastMsg = By.xpath("//div[contains(text(),'Lead created successfully')]");
    private By searchInput = By.xpath("//input[@data-testid='leads-search-input']");
    private By firstTableResultName = By.xpath("//td[contains(@data-testid, 'lead-name-')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    /**
     * Navigates to the Lead Creation modal by clicking the 'Create Lead' button.
     */
    public void clickCreateLead() {
        wait.until(ExpectedConditions.elementToBeClickable(createLeadBtn)).click();
    }

    /**
     * Validates if the success toast notification appears after lead creation.
     * @return boolean - true if toast is visible, false otherwise.
     */
    public boolean isLeadCreatedSuccessfully() {
        try {
            // Wait for both title and message of the toast to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToastTitle));
            WebElement message = driver.findElement(successToastMsg);

            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Searches for a lead by name and verifies if it appears as the first result in the table.
     * @param leadName - The name of the lead to search for.
     * @return boolean - true if names match, false otherwise.
     */
    public boolean searchAndVerifyLead(String leadName) {
        try {
            // Interact with search field
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            searchField.clear();
            searchField.sendKeys(leadName);

            // Added a small sync wait for the table to filter results
            Thread.sleep(1500);

            // Locate the lead name in the filtered table result
            WebElement leadInTable = wait.until(ExpectedConditions.visibilityOfElementLocated(firstTableResultName));
            String actualName = leadInTable.getText().trim();

            return actualName.equals(leadName);
        } catch (Exception e) {
            return false;
        }
    }
}