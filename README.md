# BDD Cucumber Automation Framework

This repository contains a **BDD-based automation testing framework** built using **Selenium, Cucumber, TestNG, Maven, Allure**, and **Jenkins**.  
The framework is designed for **scalable, CI/CD-friendly test execution** with support for **headless runs, retries of failed scenarios, and rich reporting**.

---

## 🔧 Tech Stack

- Java 17
- Selenium 4
- Cucumber 7
- TestNG
- Maven
- Allure Reporting
- Jenkins
- GitHub

---

## ▶️ Prerequisites

- Java 17 installed and configured
- Maven installed
- Chrome / required browser installed
- Allure CLI installed

Verify:
```bash
java -version
mvn -version
allure --version
```
                    ## ▶️ How to Run Tests

## Run All Scenarios
```bash
- mvn clean test
```

## Run only Failed Scenarios Only
````bash
mvn test -Dtest=FailedTestRunner
````

## Run with Automatic Retry (Recommended for CI)
````bash
mvn clean test || mvn test -Dtest=FailedTestRunner
````

            🔁 Retry Mechanism (Cucumber Rerun Plugin)

This framework implements retry at scenario level using Cucumber’s rerun plugin, which is the recommended and industry-standard approach.

**How Retry Works**
1. All scenarios are executed using TestRunner
2. Failed scenarios are captured into: target/rerun.txt
3. FailedTestRunner re-executes only the failed scenarios

Important Points

- Retry happens at scenario level, not step level
- Retry is not automatic unless a second execution is triggered
- rerun.txt is created only if at least one scenario fails
- If all scenarios pass, rerun.txt will not exist

                      🧪 Test Runners

- TestRunner
Executes all scenarios

- FailedTestRunner
Executes only scenarios listed in target/rerun.txt

**Both runners support:**
- Allure reporting
- Jenkins execution
- Headless mode

                          📊 Allure Reporting
  **Generate and View Report**
````bash
allure serve target/allure-results
````

**Allure Features**
- Scenario and step-level execution details
- Automatic screenshot attachments
- Clear failure analysis
- Retry visibility
- CI/CD-friendly reporting

                          📸 Screenshot Handling

- Screenshots are captured only when a scenario fails
- Screenshots are automatically attached to Allure reports
- No manual screenshot file access is required
- This ensures clean reporting without unnecessary artifacts.


                        🧠 Headless Execution

The framework supports headless browser execution, useful for:
- CI pipelines
- Jenkins jobs
- Faster execution

Headless mode is controlled via configuration.
Example: in properties file
headless=true


                    ⚙️ Configuration Management
Execution behavior is controlled using a configuration file.

Example configuration: in properties file
browser=chrome
execution=web
headless=true

This allows environment changes without code modification.


                    🚀 Jenkins CI/CD Integration

- The framework is fully compatible with Jenkins pipelines.

- Recommended Jenkins Execution Command
````bash
mvn clean test || mvn test -Dtest=FailedTestRunner
````

**CI Benefits**
- Automatic retry of failed scenarios
- Headless execution support
- Allure report generation
- Stable and repeatable builds


                    📁 Execution Output

- After execution, the following artifacts are generated:

target/
├── allure-results
├── rerun.txt   (only if failures occur)


                    📌 Usage Guidelines

- Always execute tests via Runner class or Maven
- Do not run feature files directly if retry is required
- Retry should be limited to avoid masking real issues
- Scenario-level retry ensures clean and stable execution


                    ✅ Best Practices Followed

- Scenario-level retry using Cucumber rerun plugin
- Config-driven execution
- CI/CD-ready design
- Clean reporting with Allure
- Reliable failure diagnostics


                        📝 Summary

**This framework demonstrates:**
- Real-world BDD automation implementation
- Proper handling of flaky failures using rerun strategy
- Clean integration with Jenkins and Allure
- Scalable and maintainable execution approach

                    

                    👨‍💻 Author 
                    Naveen Shekar
        Lead SDET | Automation & Quality Engineering