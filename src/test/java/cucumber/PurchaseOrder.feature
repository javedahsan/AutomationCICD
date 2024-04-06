@tag
Feature: Purchase order for Ecommerce website
  I want to use this template for my feature file
 
  Background:
    Given I landed on Ecommerce Page
    
  
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                  | password | productName |
      | javedmohamd@gamil.com | Mar@#456 | ZARA COAT 3 |
  