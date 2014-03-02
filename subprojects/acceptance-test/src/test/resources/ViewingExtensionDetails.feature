Feature: viewing extension details
  In order to get more information about the extension
  As a user
  I want to see the extensions information in the extension details window

  Background:
    Given I open the installed extensions window

  Scenario: viewing extension details
    When I open the extension details window
    Then I should see the extensions name
    And I should see the extensions description
    And I should see the extensions website
