## Functional tests framework

This is a template of functional test automation framework based on Java and Selenium 3.* with TestNG and Allure. Its include all necessary libraries in pom.xml file and in /main/resources folder. You can simple add new test in created tests files or create your own.

### Getting Started

**Prerequisites**

To run this suite from command line you should install Maven and Allure command-line interpreters on your local machine.

**Running tests**

To start suite from CLI run following command: `mvn clean test`.
To generate report run: `allure results`and to open on local server run: `allure serve`. Or simply run `allure serve allure-results`.
In order to do this in IntelliJ first, you should click configurations. Select maven, and write the maven “clean test” command as shown below and then click OK.

**Build With**

 - Maven - Dependency Management and project builder
 - Selenium WebDriver - basic testing tool to interact with browsers
 - TestNG - testing framework to group, run tests
 - Allure - report generation

**Author**

 - Kovtun Ihor - AQA Engineer
