Feature: Proses Pembayaran

  Background:
    Given the user is logged in

  @positive
  Scenario: Pembayaran berhasil dengan AvestaPay
    And the user is on the product listing page
    When the user clicks pesan on a product from "Warung Marcel"
    And the modal detail product should be shown
    And the user clicks Tambahkan ke keranjang
    Then the product should be added to the cart successfully

    When User membuka halaman checkout
    And User mengklik tombol Konfirmasi checkout
    Then User dialihkan ke halaman "Konfirmasi Pembayaran"

    And User mengklik tombol "Bayar dengan AvestaPay"
    Then User diarahkan ke halaman "Pembayaran"
    And User memasukkan password pembayaran
    And User mengklik tombol Konfirmasi pembayaran
    Then Muncul pesan berhasil "Pembayaran berhasil"

  @negative
  Scenario: Gagal bayar karena saldo kurang
    And the user is on the product listing page
    When the user clicks pesan on a product from "Warung Marcel"
    And the modal detail product should be shown
    And the user clicks Tambahkan ke keranjang
    Then the product should be added to the cart successfully

    When User membuka halaman checkout
    And User menambah kuantitas produk hingga total melebihi saldo
    And User mengklik tombol Konfirmasi checkout
    Then User dialihkan ke halaman "Konfirmasi Pembayaran"

    And User mengklik tombol "Bayar dengan AvestaPay"
    Then User diarahkan ke halaman "Pembayaran"
    And User memasukkan password pembayaran
    And User mengklik tombol Konfirmasi pembayaran
    Then Muncul pesan error "Saldo tidak mencukupi"

