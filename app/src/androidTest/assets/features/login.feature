Feature: Login
    Perform login on userName and password are inputted

  Scenario Outline: Input userName and password in wrong format
   Given I see the login page
    When I enter userName "<userName>"
    And I enter passwrod "<password>"
    And I click Login button
    Then I should see error message

    Examples:
        | userName | password  |
        | test     | lemoncake |
        | test     |           |
        |          | password |

  Scenario: User can login with valid user name and password
    Given I see the login page
    When I login with user name "crazydog335" and password "venture"
    Then I see the main page

