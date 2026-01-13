package driver;

import config.ConfigReader;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {

    public static WebDriver createWebDriver(String browser){
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.get("headless"));
        if (browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            if (isHeadless){
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
            }
            return new ChromeDriver(options);
        } throw new RuntimeException("Browser not supported");
    }
}
