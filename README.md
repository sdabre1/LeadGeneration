Lead Manager SaaS - QA Automation Framework
Project Overview
This repository contains a comprehensive QA assessment for the Lead Manager application. It includes manual test documentation, robust UI automation using Selenium, and API validation using Rest Assured.

Tech Stack
Language: Java 1.8

Framework: TestNG

UI Automation: Selenium WebDriver (4.1.0)

API Testing: Rest Assured

Build Tool: Maven

Design Pattern: Page Object Model (POM)

Key Framework Features
Dynamic Data Generation: Implemented System.currentTimeMillis() for names and emails to ensure unique test data for every execution, preventing "Duplicate Entry" errors during parallel or sequential runs.

Stability & Synchronization: Utilized Explicit Waits (WebDriverWait) to handle asynchronous Radix UI modals and toast notifications, ensuring zero flakiness without using Thread.sleep().

Robust Locators: Prioritized the use of data-testid attributes to ensure locators remain stable even if UI styling (Tailwind classes) or DOM structure changes.

Cross-Browser Compatibility: Configured ChromeOptions with --remote-allow-origins=* to resolve WebSocket/CDP connection issues with modern Chrome versions.

API-UI Validation: Scripts verify lead creation via both the REST API and the Web UI to ensure full stack coverage.

Project Structure
Plaintext
├── docs/
│   └── Manual_Test_Cases.md      # Part 1: Manual scenarios and edge cases
├── src/
│   ├── main/java/pages/         # Page Object Classes (Locators & Actions)
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   └── CreateLeadPage.java
│   └── test/java/tests/         # Test Scripts
│       ├── BaseTest.java        # Driver initialization & Cleanup
│       ├── LeadUiTest.java      # E2E UI Flow
│       └── LeadApiTest.java     # API Functional Tests
├── pom.xml                      # Project dependencies and plugins
└── README.md                    # Project documentation
How to Run the Tests
Prerequisites: Ensure Java 1.8 and Maven are installed on your machine.

Environment Setup: The framework uses WebDriverManager to handle driver binaries automatically based on your browser version.

Execution:
Open your terminal in the project root and run:

Bash
mvn clean test
Reporting: After execution, detailed test results and logs can be found in the target/surefire-reports folder.

Deliverables
Part 1: Detailed Manual Test Cases (Positive, Negative, and Edge cases).

Part 2: UI Automation Suite covering Login, Lead Creation, and Search Verification.

Part 3: API Automation Suite for Authentication and Lead Generation.