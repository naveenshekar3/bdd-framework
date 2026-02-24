package driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServerManager {
    private static AppiumDriverLocalService service;

    public static void startService(){
        AppiumServiceBuilder builder = new AppiumServiceBuilder().
                withAppiumJS(new File(""))
                .withIPAddress("")
                .usingPort(4823)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL,"info");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

}
