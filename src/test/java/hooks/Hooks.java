package hooks;

import config.ConfigReader;
import driver.DriverFactory;
import driver.MobileDriverManager;
import driver.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Hooks {

    @Before
    public void setup() throws Exception {
        if (ConfigReader.get("execution").equalsIgnoreCase("web")) {
            WebDriver driver = WebDriverManager.createWebDriver(ConfigReader.get("browser"));
            DriverFactory.setDriver(driver);
        } else {
            DriverFactory.setDriver(MobileDriverManager.createMobileDriver());
        }
    }

    @After
    public void tearDown(Scenario scenario){
        WebDriver driver = DriverFactory.getDriver();
        if (driver!=null){
            try {
                File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                String path = System.getProperty("user.dir")+ "/target/screenshots/" + scenario.getName().replaceAll(" ","_")+".png";
                File dest = new File(path);
                FileUtils.copyFile(src,dest);
                byte[] byteFiles = Files.readAllBytes(dest.toPath());
                Allure.addAttachment("Screenshot - "+ scenario.getName(), "image/png", new ByteArrayInputStream(byteFiles), ".png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        DriverFactory.quitDriver();
    }

}
