Feature: Demoqa screen

  Background:
    Given user is on demoqa home page with title "demosite"
    And user should see list of options


  @textbox @smoke
  Scenario: Enter text and submit the form
    And user should see text box option from the list
    When user clicks on text box from the side menu options
    Then user should see submit form opened with the "Text Box" header
    When user scroll to enter the full name in the form
    And user enters email in the form
    And user enters current address in the form
    And user enters permanent address in the form
    Then user clicks on submit button

  @checkbox @smoke
  Scenario: Select the checkboxs
    And user should see check box option from the list
    When user clicks on check box from the side menu options
    Then user should see check box lists opened with the "Check Box" header
    When user clicks on + button
    Then user should see list of check boxes elements
    And user should select the check boxes
      | Desktop         |
      | Documents       |
      | Downloads       |

    @nestedFrames @smoke
    Scenario: Switch frames
      And user should see alert frame and windows option from the list
      When user clicks on alert frame and windows option from the side menu options
      And user should see nested frame option from the list
      When user clicks on nested frame option from the list
      Then user should see Nested Frames opens with "Nested Frames" header
      And user should see parent iframe
      Then user should get the text of child frame "Child Iframe"
