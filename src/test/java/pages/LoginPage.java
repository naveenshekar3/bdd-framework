package pages;

import config.ConfigReader;
import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonUtils;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(){
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver,this);
    }


    // Text box Elements
    @FindBy(xpath = "//div[text()=\"Elements\"]" )
    private WebElement elementsSideMenuOption;

    @FindBy(xpath = "//div[contains(@class,\"collapse show\")]//ul[@class=\"menu-list\"]")
    private WebElement listOfElementsSideMenuOptions;

    @FindBy(xpath = "//span[text()=\"TextBox\"]") // intentionally failing this to test retry mechanism by editing the xpath value Text Box as TextBox
    private WebElement textBoxOptionFromList;

    @FindBy(xpath = "//h1[text()=\"Text Box\"]")
    private WebElement textBoxFormHeader;

    @FindBy(id = "userName")
    private WebElement fullName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddress;

    @FindBy(id = "submit")
    private WebElement submitButton;

    // Check box Elements
    @FindBy(xpath = "//span[text()=\"Check Box\"]")
    private WebElement checkBoxOptionFromList;

    @FindBy(xpath = "//h1[text()=\"Check Box\"]")
    private WebElement checkBoxHeader;

    @FindBy(xpath = "//span[@class=\"rc-tree-switcher rc-tree-switcher_close\"]")
    private WebElement expandAllButton;

    @FindBy(xpath = "//span[@class=\"rc-tree-checkbox\"]")
    private List<WebElement> listOfCheckboxes;

    @FindBy(xpath = "//span[text()=\"{0}\"]")
    private WebElement checkboxElement;

    //nested frames
    @FindBy(xpath = "//div[text()=\"Alerts, Frame & Windows\"]")
    private WebElement alertFrameAndWindowsSideMenuOption;

    @FindBy(xpath = "//span[text()=\"Nested Frames\"]")
    private WebElement nestedFrameSideMenuOption;

    @FindBy(xpath = "//h1[text()=\"Nested Frames\"]")
    private WebElement nestedFramesHeader;

    @FindBy(id = "frame1")
    private WebElement frame1;

//    @FindBy(tagName = "iframe")
//    private List<WebElement> iFrameList;

    @FindBy(xpath = "//body[text()=\"Parent frame\"]")
    private WebElement parentFrameTextElement;

    @FindBy(xpath = "//p[text()=\"Child Iframe\"]")
    private WebElement childFrameTextElement;

    private WebElement sendTextToLocator(String locatorText){
        return driver.findElement(By.xpath("//span[@aria-label='Select " + locatorText + "']"));
    }



    // Mobile locators (example – Android)
    private By mobileUsername = By.id("com.demo.app:id/username");
    private By mobilePassword = By.id("com.demo.app:id/password");
    private By mobileLoginBtn = By.id("com.demo.app:id/login");

    public void login(String title){
        if (CommonUtils.isWebExecution()){
            driver.get(ConfigReader.get("url"));
            Assert.assertEquals(driver.getTitle(),title);
        }
    }

    public void listOfElementsOptionsDisplayed(){
        Assert.assertTrue(listOfElementsSideMenuOptions.isDisplayed(), "Failed to display list of elements options in side menu");
    }

    public void textOptionFromListIsDisplayed(){
        Assert.assertTrue(textBoxOptionFromList.isDisplayed(), "Failed to diaplay text box option from the elements list options in side menu");
    }

    public void clickOnTextBoxOptionFromSideMenu(){
        textBoxOptionFromList.click();
    }

    public void verifySubmitFormOpenedWithTextBoxHeader(String header){
        Assert.assertTrue(textBoxFormHeader.isDisplayed(), "Failed to display text box header in the form");
        Assert.assertEquals(textBoxFormHeader.getText(), header, "Expected is -> "+header+" But Actual is -> "+ textBoxFormHeader.getText());
    }

    public void enterFullName(){
        Assert.assertTrue(CommonUtils.ScrollToElementAndSendKeys(driver,fullName,10,3,"Naveen"),"Failed to scroll and enter full name");
    }

    public void enterEmail(){
        Assert.assertTrue(CommonUtils.ScrollToElementAndSendKeys(driver,email,10,3,"mcnaveen0@gmail.com"),"Failed to scroll and enter email");
    }
    public void enterCurrentAddress(){
        Assert.assertTrue(CommonUtils.ScrollToElementAndSendKeys(driver,currentAddress,10,3,"Bangalore"),"Failed to scroll and enter current address");
    }
    public void enterPermanentAddress(){
        Assert.assertTrue(CommonUtils.ScrollToElementAndSendKeys(driver,permanentAddress,10,3,"Mysore"),"Failed to scroll and enter permanent address");
    }

    public void clickOnSubmitButton(){
        Assert.assertTrue(CommonUtils.ScrollToElementAndClick(driver,submitButton,10,3),"Failed to scroll and click submit button");
    }

    public void verifyCheckBoxOptionFromList(){
        Assert.assertTrue(CommonUtils.waitScrollTillClickable(driver,checkBoxOptionFromList,10,3),"Failed to find the check box option from the side menu");
    }

    public void clickCheckBoxFromSideMenu(){
        Assert.assertTrue(CommonUtils.waitAndClickElement(driver,checkBoxOptionFromList,10, 5),"Failed to click on the check box from the side menu");
    }

    public void verifyCheckBoxListsWithHeader(String checkboxHeader){
        Assert.assertEquals(checkBoxHeader.getText(),checkboxHeader,"Expected is -> "+checkboxHeader+" But Actual is -> "+ checkBoxHeader.getText());
    }

    public void clickExpandButton(){
        Assert.assertTrue(CommonUtils.waitAndClickElement(driver,expandAllButton,10, 5),"Failed to click on expand all button");
    }

    public void verifyListOfExpandedCheckBoxes(){
        Assert.assertFalse(listOfCheckboxes.isEmpty(), "List of check boxes are empty");
    }

    public void selectCheckBoxes(String checkBoxesText){
        Assert.assertTrue(CommonUtils.waitScrollTillClickable(driver,sendTextToLocator(checkBoxesText),10,5),"Failed to scroll till the element ->" + checkBoxesText);
        Assert.assertTrue(CommonUtils.ScrollToElementAndClick(driver,sendTextToLocator(checkBoxesText),10,3), "Failed to select the list of check boxes");
    }

    public void verifyAlertFrameAndWindowsOptionVisible(){
        Assert.assertTrue(CommonUtils.waitScrollTillClickable(driver,alertFrameAndWindowsSideMenuOption,10,5),"Failed to locate alert Frame And Windows SideMenu Option");
    }

    public void clickOnAlertFrameAndWindowsSideMenuOption(){
        Assert.assertTrue(CommonUtils.waitAndClickElement(driver,alertFrameAndWindowsSideMenuOption,10,5),"Failed to click on alert Frame And Windows SideMenu Option");
    }

    public void verifyNestedFrameOptionUnderAlertFrameAndWindowsOption(){
        Assert.assertTrue(CommonUtils.waitScrollTillClickable(driver,nestedFrameSideMenuOption,10,5), "Failed to locate Nested Frame Option Under Alert Frame And Windows Option");
    }

    public void clickOnNestedFrameOption(){
        Assert.assertTrue(CommonUtils.waitAndClickElement(driver,nestedFrameSideMenuOption,10,5),"Failed to click on nested Frame Side Menu Option");
    }

    public void verifyNestedFramesOpensWithHeader(String nestedFrameHeader){
        Assert.assertTrue(CommonUtils.waitScrollTillClickable(driver,nestedFramesHeader,10,5), "Failed to locate nested frame header");
        Assert.assertEquals(nestedFramesHeader.getText(),nestedFrameHeader, "Expected is -> "+nestedFrameHeader+" But Actual is -> "+ nestedFramesHeader.getText());
    }

    public void verifyParentIFrameIsVisible(){
        Assert.assertTrue(CommonUtils.waitScrollTillClickable(driver,frame1,10,5), "Failed to locate parent iframe");
    }

    public void verifyChildIFrameText(String childFrameText){
        Assert.assertTrue(CommonUtils.switchIFrames(driver,frame1,childFrameTextElement), "Failed to switch iFrame");
        Assert.assertEquals(childFrameTextElement.getText(),childFrameText, "Expected is -> "+childFrameText+" But Actual is -> "+ childFrameTextElement.getText());
    }


}
