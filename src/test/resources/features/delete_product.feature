Feature: Pemilik toko deletes product

  Scenario: Pemilik toko successfully delete the product
    Given The user is on the login page
    When The user enters a valid email and password
    And The user presses the login button
    And The user goes to the mitra page
    And The user presses the dashboard button
    And The user goes to the admin page and presses the button produk
    And The user selects the product named "Dada Ayam"
    And The user presses the delete button on the selected product
    And The user presses the delete confirmation button
    Then The product was successfully deleted and the user remains on the page produk

  Scenario: Pemilik toko failed to delete the product
    Given The user is on the login page
    When The user enters a valid email and password
    And The user presses the login button
    And The user goes to the mitra page
    And The user presses the dashboard button
    And The user goes to the admin page and presses the button produk
    And The user selects the product named "Dada"
    And The user presses the delete button on the selected product
    But The user does not press the delete confirmation button
    Then The product is not deleted and remains in the product list
