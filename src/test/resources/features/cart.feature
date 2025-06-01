Feature: Cart functionality

  Scenario: User adds a product to cart successfully
    Given the user is logged in
    And the user is on the product listing page
    When the user clicks pesan on a product from "Warung Marcel"
    And the modal detail product should be shown
    And the user clicks Tambahkan ke keranjang
    Then the product should be added to the cart successfully

  Scenario: User cannot add product from a different store to cart
    Given the user is logged in
    And the user is on the product listing page
    And the user adds a product from "Warung Marcel" to the cart
    When the user attempts to add a product from "Warung Sejahtera" to the cart
    Then the cart count should not increase
    And an modal message should be shown
