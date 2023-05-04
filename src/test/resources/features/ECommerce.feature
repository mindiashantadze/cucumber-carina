Feature: ECommerce Site
  Background:
    Given User opens the website

    Scenario: Standard user checkout
      Given User logs in as "standard_user"
      And User adds products to the cart
      Then User should see label on cart icon with product count
      And User goes to the cart page
      Then User should see list of his/her products in cart
      And User goes to the checkout page
      When User enters required info
      Then User should see list of his/her products in checkout
      When User clicks on finish button
      Then User should see thank you message
