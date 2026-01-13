package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.login();
    }

    @Given("user is on login page and checks the title {string}")
    public void user_logins_and_checks_title(String title){
        loginPage.loginAndCheckTitle(title); // intentionally failing this test
    }

//    @When("user enters valid credentials")
//    public void enter_credentials() {
//        // Write code here that turns the phrase above into concrete actions
//    }
//
//    @Then("user should login successfully")
//    public void verify_login() {
//        // Write code here that turns the phrase above into concrete actions
//    }
}
