package driver;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverManager {
    public static AppiumDriver createMobileDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("appPackage", "com.workongrid.genus");
        capabilities.setCapability("appActivity", ".MainActivity");


        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }
}
