package driver;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    public static WebDriver createWebDriver(String browser){
        if (browser.equalsIgnoreCase("chrome")){
            return new ChromeDriver();
        } throw new RuntimeException("Browser not supported");
    }
}
