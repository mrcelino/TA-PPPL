Feature: Cart functionality

  Background:
    Given the user is logged in
    And the user is on the product listing page

  Scenario: User adds a product to cart successfully
    When the user clicks pesan on a product from "Warung Marcel"
    And the modal detail product should be shown
    And the user clicks Tambahkan ke keranjang
    Then the product should be added to the cart successfully

  Scenario: User cannot add product from a different store to cart
    And the user adds a product from "Warung Marcel" to the cart
    When the user attempts to add a product from "Warung Sejahtera" to the cart
    Then the cart count should not increase
    And an modal message should be shown

  @bva @ep
  Scenario Outline: User sets product quantity with BVA/EP values
    When the user clicks pesan on a product from "Warung Marcel"
    And the modal detail product should be shown
    When the user sets the quantity to <quantity>
    And the user clicks Tambahkan ke keranjang
    Then <expectedResult>

    Examples:
      | quantity | expectedResult                                       |
      | 1        | the product should be added to the cart successfully |
      | 13       | the product should be added to the cart successfully |
      | 14      | an error message should be shown indicating stock is insufficient |

