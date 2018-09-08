Feature: Login
    Perform login on email and password are inputted

    @login-feature
  Scenario: User can login with valid user name and password
    Given I see the login page
    When I login with user name "crazydog335" and password "venture"
    Then I see the main page

