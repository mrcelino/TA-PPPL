Feature: Registrasi User

  @positive
  Scenario: Registrasi berhasil sebagai Pembeli
    Given User membuka halaman registrasi sistem Avesta
    When Mengisi semua field dengan data valid:
      | Nama Depan      | John          |
      | Nama Belakang   | Doe           |
      | Email           | johnn@doe.com  |
      | Nomor HP        | 081234567890  |
      | Sandi           | Password123!  |
      | Konfirmasi Sandi| Password123!  |
    And Mencentang checkbox persetujuan
    And Mengklik tombol 'Daftar'
    Then Sistem menyimpan data user dengan role Pembeli
    And Mengarahkan ke halaman login


  @negative
  Scenario: Registrasi gagal karena email tidak valid
    Given User membuka halaman registrasi sistem Avesta
    When Mengisi field dengan data:
      | Nama Depan      | John          |
      | Nama Belakang   | Doe           |
      | Email           | user@         |
      | Nomor HP        | 081234567890  |
      | Sandi           | Password123!  |
      | Konfirmasi Sandi| Password123!  |
    And Mencentang checkbox persetujuan
    And Mengklik tombol 'Daftar'
    Then Sistem menampilkan pesan error "@"