package utils;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CommonUtils {

    public static boolean isWebExecution() {
        return ConfigReader.get("execution").equalsIgnoreCase("web");
    }


    public static boolean waitAndClickElement(WebDriver driver, WebElement element, int timeoutInSeconds, int maxScrolls) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            waitScrollTillClickable(driver,element,timeoutInSeconds,maxScrolls);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean waitAndSendKeys(WebDriver driver, WebElement element, int timeoutInSeconds, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean waitScrollTillClickable(
            WebDriver driver,
            WebElement element,
            int timeoutInSeconds,
            int maxScrolls) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < maxScrolls; i++) {
            try {
                js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return true; // clickable means overlay is gone
            } catch (TimeoutException | ElementClickInterceptedException e) {
                js.executeScript("window.scrollBy(0, 200);"); // scroll page, not element
            }
        }
        return false;
    }


    public static boolean ScrollToElementAndSendKeys(WebDriver driver, WebElement element, int timeoutInSeconds, int maxScroll, String text) {
        try {
            if (waitScrollTillClickable(driver, element, timeoutInSeconds, maxScroll))
                waitAndSendKeys(driver, element, timeoutInSeconds, text);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean ScrollToElementAndClick(WebDriver driver, WebElement element, int timeoutInSeconds, int maxScroll) {
        try {
            if(waitScrollTillClickable(driver, element, timeoutInSeconds, maxScroll))
                waitAndClickElement(driver, element, timeoutInSeconds, maxScroll);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean switchIFrames(WebDriver driver, WebElement parentIframe, WebElement childElement){

        driver.switchTo().defaultContent();
        driver.switchTo().frame(parentIframe);

        List<WebElement> childIframes = driver.findElements(By.tagName("iframe"));

        for (WebElement childIframe : childIframes){
            try {
                driver.switchTo().frame(childIframe);
                if (childElement.getText().equals("Child Iframe")) {
                    return true;
                }
            }catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean handleAlerts(WebDriver driver, String alertAction, String value){
        Alert alert = driver.switchTo().alert();
        switch (alertAction.toLowerCase()){
            case "accept":
                alert.accept();
                break;
            case "dismiss":
                alert.dismiss();
                break;
            case "sendKeys":
                alert.sendKeys(value);
                break;
        }
        return true;
    }

    public static void selectDropDownWithoutSelectClass(WebDriver driver, By dropdown, By options, By value) {
        driver.findElement(dropdown).click();

        for (WebElement option : driver.findElements(options)) {
            if (option.getText().equals(value)) {
                option.click();
                break;
            }
        }
    }

    // Actions class is used for Selenium and not for mobile
    public static void ActionClass(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        action.click(element).perform();
        action.contextClick(element).perform();
        action.doubleClick(element).perform();
        WebElement src = driver.findElement(By.id(""));
        WebElement target = driver.findElement(By.id(""));
        action.dragAndDrop(src, target).perform();
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform(); // Select all text
        action.clickAndHold(src).moveToElement(target).release().perform();
        action.sendKeys(target, "Naveen").perform();
        action.moveToElement(target).click().build().perform(); // build() -> Combine actions, perform() -> Execute actions
    }

    public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY) {
        // Create a Virtual Finger
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger"); // Creates a virtual touch pointer, Represents a finger touching the screen
        // Create an Action Sequence
        Sequence swipe = new Sequence(finger, 1); //Groups multiple touch actions into one gesture, The number 1 is the sequence ID
        // Move Finger to Starting Point (WITHOUT TOUCHING)
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY)); // Moves the finger above the screen to the start position. (Duration.ZERO -> Instant movement, Origin.viewport() -> Coordinates relative to screen, 500, 1000 -> X, Y start coordinates)
        // Touch the Screen (Finger Down)
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); // Simulates finger touching the screen, Why LEFT?, W3C spec treats touch like a mouse button , LEFT button = primary touch
        // Drag Finger to Destination (Swipe)
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY)); // Moves the finger while still touching the screen, 600 ms -> Swipe duration (speed) Longer duration → slower swipe and Shorter duration → faster swipe, viewport() -> Screen-based, 500, 300 -> End coordinates
        // Lift Finger (Release)
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Simulates lifting the finger off the screen
        // Execute the Gesture
        driver.perform(Collections.singletonList(swipe)); // Sends the entire gesture to Appium, Executes actions in order, singletonList because we have one finger, Multi-finger gestures use multiple sequences
        // Gesture Timeline (Visualization in Words)
        // Finger moves → Finger touches → Finger drags → Finger lifts
    }

    public static void swipeUp(AppiumDriver driver) {

        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        driver.perform(Collections.singletonList(swipe));
    }

    // For mobile and not for selenium
    public static void scrollUntilElementVisible(AppiumDriver driver, By locator, int maxScrolls) {

        int count = 0;

        while (count < maxScrolls) {
            try {
                if (driver.findElement(locator).isDisplayed()) {
                    return;
                }
            } catch (Exception e) {
                // Element not found, scroll
            }

            swipeUp(driver);
            count++;
        }

        throw new RuntimeException("Element not found after scrolling");
    }
}
