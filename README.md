# Selenium Tests for Entrata.com

## Project Description

This project contains a set of **Selenium WebDriver** tests written in **Java** with **TestNG** as the testing framework. The tests validate and explore different functionalities of [entrata.com](https://www.entrata.com), such as navigation, interaction with forms (without submission), and verifying dynamic content. The project includes **AssertJ** for assertions and generates **Extent Reports** in the `Reports` folder to document the test results.

## Prerequisites

Ensure you have the following installed:

- **Java 11** or higher
- **Maven** for dependency management
- **Selenium WebDriver**
- **TestNG** for running tests
- The browser's WebDriver (e.g., **ChromeDriver**, **GeckoDriver** for Firefox)

## Project Setup

### Clone the Repository

Clone the project repository to your local machine:

```bash
git clone https://github.com/DevGops/Entrata.git
cd Entrata
```
### Install Dependencies

Run the following Maven command to download and install all the dependencies:

```bash
mvn clean install
```
Maven will automatically install dependencies specified in the pom.xml file, including:

1. Selenium WebDriver
2. AssertJ for fluent assertions
3. WebDriverManager to manage WebDriver binaries
4. ExtentReports for reporting
5. Java Faker for generating random test data

## Folder Structure
```css
    │   .gitignore
    │   pom.xml
    │   README.md
    │   testng.xml
    │
    ├───Logs
    │       logs.txt
    │
    ├───Reports
    │   ├───screenshots
    │   └───Entrata_YYYYMMDD_HHMMSS.html (Generated Extent Reports)
    │
    └───src
       ├───main
       │   ├───java
       │   │   ├───controllers
       │   │   ├───listeners
       │   │   └───utils
       │   └───resources
       └───test
           └───java
               ├───pageObjects
               └───testsEntrata
```
1. **Logs:** Contains log files generated during the tests.
2. **Reports:** Extent reports and screenshots are generated here.
3. **src/main:** Contains core Java classes such as utilities, test setup, and configurations.
4. **src/test:** Contains the test classes and Page Object Models (POM).

## Running the Tests
### Using Maven: 

Execute the following command to run all the tests:

```bash
mvn test  
```
This will run the TestNG test suite defined in testng.xml and generate a report in the Reports folder.

## View Extent Reports: 
The test results, along with screenshots for failed tests, will be generated in HTML format in the **Reports** folder. Open the .html report to review the test execution summary.

## Key Classes and Tests
1. **BaseTest.java:** Contains setup and teardown logic for tests (e.g., browser setup via WebDriverManager).

2. **BrowserFactory.java:** Factory class for managing different browser instances (Chrome, Firefox, etc.).

3. **HomePage.java, SignInPage.java (POM):** Encapsulate the UI elements and actions related to Entrata pages.

4. **LoginTest.java:** Test case for verifying the login functionality for Resident and Manager.

5. **NavigationTest.java:** Test validates the navigation flow through different pages on the website.

6. **WatchDemo.java:** Test interacts with Watch Demo form.

## Extent Reporting
### Extent Report: 
HTML reports are generated for each test execution, located in the Reports folder. The reports provide details such as test steps, screenshots for failed tests, and overall test status.

### Screenshots: 
Any failed test steps will have screenshots saved in the Reports/screenshots folder. These screenshots are also included in the HTML reports for better traceability.

## Assertions
AssertJ is used for making assertions in tests, offering a fluent API to verify outcomes. Examples include:

```java
import org.assertj.core.api.Assertions;

Assertions.assertThat(driver.getTitle()).isEqualTo("Entrata");
```
## Configuration Files
### Config.properties: 
Contains environment-specific settings such as base URLs and timeouts.
### log4j.properties: 
Configuration for logging using Log4j.

### Important Notes

```dtd
Avoided form submissions: 
        The tests are executed without any form submission.
```

