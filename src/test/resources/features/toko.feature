Feature: Update Toko

  @negative
  Scenario: Mengupdate data toko dengan field alamat kosong
    Given User membuka halaman login sistem Avesta
    When User login dengan email "marcelino@mail.ugm.ac.id" dan password "12345678"
    And User mengklik Dashboard di navbar
    And User mengklik Toko di sidebar
    And User mengklik tombol Ubah
    When User mengupdate data toko dengan:
      | Nama Warung    | Warung Marcel  |
      | Alamat Warung  | [empty]        |
      | Deskripsi      | Deskripsi Toko |
      | Nomor HP       | 123            |
      | Kelurahan      | Sinduadi       |
    And User mengklik tombol Simpan
    Then Sistem menampilkan pesan error alamat "Please fill out this field"

  @positive
  Scenario: Mengupdate data toko dengan field valid
    Given User membuka halaman login sistem Avesta
    When User login dengan email "marcelino@mail.ugm.ac.id" dan password "12345678"
    And User mengklik Dashboard di navbar
    And User mengklik Toko di sidebar
    And User mengklik tombol Ubah
    When User mengupdate data toko dengan:
      | Nama Warung    | Warung Marcel     |
      | Alamat Warung  | Jl Sudirman No 55 |
      | Deskripsi      | Deskripsi Toko    |
      | Nomor HP       | 123               |
      | Kelurahan      | Sinduadi          |
    And User mengklik tombol Simpan
    Then Sistem menyimpan data toko yang diperbarui