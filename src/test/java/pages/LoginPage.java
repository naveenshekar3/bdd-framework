package pages;

import config.ConfigReader;
import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import utils.CommonUtils;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(){
        this.driver = DriverFactory.getDriver();
    }

    private By webUsername = By.id("username");
    private By webPassword = By.id("password");
    private By webLoginBtn = By.id("loginBtn");

    // Mobile locators (example – Android)
    private By mobileUsername = By.id("com.demo.app:id/username");
    private By mobilePassword = By.id("com.demo.app:id/password");
    private By mobileLoginBtn = By.id("com.demo.app:id/login");

    public void login(){
        if (CommonUtils.isWebExecution()){
            driver.get(ConfigReader.get("url"));
            System.out.println("TITLE IS -----> "+driver.getTitle());
        }
    }

    public void loginAndCheckTitle(String expectedTitle){
        if (CommonUtils.isWebExecution()){
            driver.get(ConfigReader.get("url"));
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle,expectedTitle, "Actual title is not matching with expected title, Actual title -> "+ actualTitle+" expected title -> "+ expectedTitle);
        }
    }
}
