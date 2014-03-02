Feature: Extension loading
  In order to provide the extensions functionality to users
  As a developer
  I want to ensure the extension is loaded in BlueJ

  Scenario: The extension is loaded successfully
    Given the extension is placed in the extensions folder
    When I open the installed extensions window
    Then I should see the extension listed