package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Given("user is on demoqa home page with title {string}")
    public void user_is_on_demoqa_page(String title) {
        // Write code here that turns the phrase above into concrete actions
        loginPage.login(title);
    }

    @And("user should see list of options")
    public void user_should_see_list_of_options(){
        loginPage.listOfElementsOptionsDisplayed();
    }

    @And("user should see text box option from the list")
    public void user_should_see_text_box_option(){
        loginPage.textOptionFromListIsDisplayed();
    }

    @When("user clicks on text box from the side menu options")
    public void user_clicks_on_text_box_option(){
        loginPage.clickOnTextBoxOptionFromSideMenu();
    }

    @Then("user should see submit form opened with the {string} header")
    public void user_should_see_submit_form_opened(String header){
        loginPage.verifySubmitFormOpenedWithTextBoxHeader(header);
    }

    @When("user scroll to enter the full name in the form")
    public void user_scroll_to_enter_the_full_name_in_the_form() {
        loginPage.enterFullName();
    }

    @And("user enters email in the form")
    public void enter_email_in_the_form(){
        loginPage.enterEmail();
    }

    @And("user enters current address in the form")
    public void enter_current_address_in_the_form(){
        loginPage.enterCurrentAddress();
    }

    @And("user enters permanent address in the form")
    public void enter_permanent_address_in_the_form(){
        loginPage.enterPermanentAddress();
    }

    @And("user clicks on submit button")
    public void click_on_submit_button() throws InterruptedException {
        loginPage.clickOnSubmitButton();
        Thread.sleep(3000);
    }

    @And("user should see check box option from the list")
    public void user_should_see_check_box_option_from_the_list(){
        loginPage.verifyCheckBoxOptionFromList();
    }

    @When("user clicks on check box from the side menu options")
    public void user_clicks_on_check_box_from_the_side_menu_options(){
        loginPage.clickCheckBoxFromSideMenu();
    }

    @Then("user should see check box lists opened with the {string} header")
    public void user_should_see_check_box_lists_opened_with_the_Check_Box_header(String checkboxHeader){
        loginPage.verifyCheckBoxListsWithHeader(checkboxHeader);
    }

    @When("user clicks on + button")
    public void user_clicks_on_plus_button(){
        loginPage.clickExpandButton();
    }

    @Then("user should see list of check boxes elements")
    public void user_should_see_list_of_check_boxes_elements(){
        loginPage.verifyListOfExpandedCheckBoxes();
    }

    @And("user should select the check boxes")
    public void user_should_select_the_check_boxes(DataTable dataTable) throws InterruptedException {
        Thread.sleep(2000);
        for (String s: dataTable.values()){
            loginPage.selectCheckBoxes(s);
        }
        Thread.sleep(2000);
    }

    @And("user should see alert frame and windows option from the list")
    public void user_should_see_alert_frame_and_windows_option_from_the_list(){
        loginPage.verifyAlertFrameAndWindowsOptionVisible();
    }

    @When("user clicks on alert frame and windows option from the side menu options")
    public void user_clicks_on_alert_frame_and_windows_option_from_the_side_menu_options(){
        loginPage.clickOnAlertFrameAndWindowsSideMenuOption();
    }

    @And("user should see nested frame option from the list")
    public void user_should_see_nested_frame_option_from_the_list(){
        loginPage.verifyNestedFrameOptionUnderAlertFrameAndWindowsOption();
    }

    @When("user clicks on nested frame option from the list")
    public void user_clicks_on_nested_frame_option_from_the_list(){
        loginPage.clickOnNestedFrameOption();
    }

    @Then("user should see Nested Frames opens with {string} header")
    public void user_should_see_Nested_Frames_opens_with_nested_frames_header(String nestedFrameHeader){
        loginPage.verifyNestedFramesOpensWithHeader(nestedFrameHeader);
    }

    @And("user should see parent iframe")
    public void user_should_see_parent_iframe(){
        loginPage.verifyParentIFrameIsVisible();
    }

    @Then("user should get the text of child frame {string}")
    public void user_should_get_the_text_of_child_frame(String childFrameText){
        loginPage.verifyChildIFrameText(childFrameText);
    }
}
