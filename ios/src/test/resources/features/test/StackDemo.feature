Feature: BrowserStack test

  Scenario: BStack Sample Test - Verify if displayed text matches entered text
    Given I try to find Text Button in Sample App
    When I type in 'hello@browserstack.com' in the Text Input field
    Then I should get the entered text in the Text Output field
