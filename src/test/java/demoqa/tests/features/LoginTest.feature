Feature: Login Test

  Scenario: login test with valid username and password
    Given open the app
    Then write user name "apple"
    Then write password "banana"
    Then click on login button
    Then validate login success "login successful"
    Then close the app



  #Scenario: login with