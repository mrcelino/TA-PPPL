Feature: Add Employee

  Scenario: Owner successfully adds a new employee
    Given the owner is logged in
    And the owner is on Mitra page "https://avesta.cloud/mitra"
    When the owner clicks the Dashboard button
    And the owner is redirected to Dashboard page "https://avesta.cloud/admin"
    And the owner clicks the Karyawan button
    And the owner is redirected to Karyawan page "https://avesta.cloud/admin/karyawan"
    And the owner clicks the Tambah Karyawan button
    And the owner is redirected to Tambah Karyawan page "https://avesta.cloud/admin/tambah-karyawan"
    And the owner enters first name "TEST10" and last name "yogi" and phone "081234567890" and email "yogi1@gmail.com"
    And the owner uploads a photo "C:\\Users\\A S U S\\Pictures\\Screenshots\\KAKAO\\Screenshot 2024-01-08 053120.png"
    And the owner clicks the Buat button
    Then a new employee should be successfully added

  Scenario: Owner fails to add an employee with already registered email
    Given the owner is logged in
    And the owner is on Mitra page "https://avesta.cloud/mitra"
    When the owner clicks the Dashboard button
    And the owner is redirected to Dashboard page "https://avesta.cloud/admin"
    And the owner clicks the Karyawan button
    And the owner is redirected to Karyawan page "https://avesta.cloud/admin/karyawan"
    And the owner clicks the Tambah Karyawan button
    And the owner is redirected to Tambah Karyawan page "https://avesta.cloud/admin/tambah-karyawan"
    And the owner enters first name "cek" and last name "salah2" and phone "081234567890" and email "yogi9@gmail.com"
    And the owner clicks the Buat button
    Then the registration should show a message "The email has already been taken."
