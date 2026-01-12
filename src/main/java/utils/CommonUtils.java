package utils;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class CommonUtils {

    public static boolean isWebExecution(){
        return ConfigReader.get("execution").equalsIgnoreCase("web");
    }

    public static void selectDropDownWithoutSelectClass(WebDriver driver, By dropdown, By options, By value){
        driver.findElement(dropdown).click();

        for (WebElement option: driver.findElements(options)){
            if (option.getText().equals(value)){
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
        action.dragAndDrop(src,target).perform();
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform(); // Select all text
        action.clickAndHold(src).moveToElement(target).release().perform();
        action.sendKeys(target,"Naveen").perform();
        action.moveToElement(target).click().build().perform(); // build() -> Combine actions, perform() -> Execute actions
    }

    public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY) {
        // Create a Virtual Finger
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger"); // Creates a virtual touch pointer, Represents a finger touching the screen
        // Create an Action Sequence
        Sequence swipe = new Sequence(finger,1); //Groups multiple touch actions into one gesture, The number 1 is the sequence ID
        // Move Finger to Starting Point (WITHOUT TOUCHING)
        swipe.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX,startY)); // Moves the finger above the screen to the start position. (Duration.ZERO -> Instant movement, Origin.viewport() -> Coordinates relative to screen, 500, 1000 -> X, Y start coordinates)
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
        int endY   = (int) (size.height * 0.2);

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
