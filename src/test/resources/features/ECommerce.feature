Feature: ECommerce Site
  Background:
    Given User opens the website

    Scenario: Standard user checkout
      Given User logs in as "standard_user"
      And User adds products to the cart
      Then User should see list of his/her products
      And User goes to the checkout page
      Then User should see list of his/her products
      When User clicks on finish button
      Then User should see thank you message
