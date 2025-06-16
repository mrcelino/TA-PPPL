Feature: Product Search

  Scenario: User successfully searches for chicken products
    Given the user is logged in for search
    And the user is on the homepage
    When the user enters "Sayap" in the search bar
    And the user clicks the Search button
    Then the system should display a list of chicken products
    And the search results should be relevant to the keyword

  Scenario: User fails to find products with invalid keyword
    Given the user is logged in for search
    And the user is on the homepage
    When the user enters "Kucing" in the search bar
    And the user clicks the Search button
    Then the search should show a message "Tidak ada produk ditemukan untuk"

