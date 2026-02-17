package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;
import pages.CreateLeadPage;

public class VerifyLeadGenerationViaUITest extends BaseTest {

    @Test(description = "Verify End-to-End Lead Creation Flow: Login -> Create -> Search")
    public void testCreateLeadFlow() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);
        CreateLeadPage createLead = new CreateLeadPage(driver);

        // Generate dynamic data to avoid duplicate entry issues
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uniqueName = "Osmos_Test_" + timestamp;
        String uniqueEmail = "osmos_" + timestamp + "@test.com";

        // 1. Login to the application
        login.login("admin@company.com", "Admin@123");

        // 2. Open Create Lead Modal and fill details
        dashboard.clickCreateLead();

        // Matching the parameters: Name, Email, Company, DealValue, Source
        createLead.fillLeadDetails(uniqueName, uniqueEmail, "High Tech Corp", "5000", "LinkedIn");

        // 3. Step Validation: Check for success toast notification
        Assert.assertTrue(dashboard.isLeadCreatedSuccessfully(),
                "Success toast notification was not displayed after lead creation.");

        Thread.sleep(10000);
        // 4. Persistence Validation: Search for the lead and verify it in the results table
        Assert.assertTrue(dashboard.searchAndVerifyLead(uniqueName),
                "The newly created lead was not found in the dashboard search results.");
    }
}