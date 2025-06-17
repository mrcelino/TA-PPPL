# 🧪 Avesta Web E2E Test Suite

Automated end-to-end (E2E) test suite for **Avesta**, a web-based marketplace for selling raw chicken and related food products. This test suite is designed to validate critical user flows, from user registration to transaction completion.

## 🔧 Frameworks used

- 🧷 **Cucumber (Gherkin)** for BDD
- 🧪 **Selenium WebDriver** for browser automation
- 🛠️ **Java** as the main programming language

---

## 📦 Test Modules & Assignments

The tests are grouped into three core modules with respective feature owners (PIC):

### 🔐 Authentication Module

Responsible for verifying account access and security flows.

| Feature    | Description                                | Type                | PIC    |
|------------|--------------------------------------------|---------------------|--------|
| Registrasi | Daftar akun dengan valid/invalid input     | Positive / Negative | Robbi  |
| Login      | Login akun dengan valid/invalid input   | Positive / Negative | Najwan |

### 🛒 Pembeli Module

Covers customer-side functionalities including search, cart, and payment.

| Feature         | Description                                             | Type                | PIC    |
|-----------------|---------------------------------------------------------|---------------------|--------|
| Cari Ayam       | Mencari produk dengan kata kunci                        | Positive / Negative | Yogi   |
| Tambah Keranjang| Tambah produk ke keranjang, termasuk BVA/EP            | Positive / Negative | Marcel |
| Pembayaran      | Pembayaran produk dengan AvestaPay                     | Positive / Negative | Marcel |

#### 🧪 Detail Tambah Keranjang Test Suite

**Positive:**
- Menambah produk ke keranjang dari toko yang sama
- Menambah produk dengan jumlah 1 dan 13 _(valid BVA/EP)_

**Negative:**
- Menambah produk dari toko berbeda
- Menambah produk dengan jumlah 14 _(melebihi stok – BVA/EP)_


### 🧑‍🍳 Pemilik Toko Module

Tests to validate **store management functionalities** such as employee management and product control.

| Feature         | Description                                               | Type                | PIC   |
|-----------------|-----------------------------------------------------------|---------------------|--------|
| Tambah Karyawan | Menambahkan karyawan baru ke toko                         | Positive / Negative | Yogi   |
| Update Toko     | Mengubah data toko pada field nama toko dan alamat        | Positive / Negative | Robbi  |
| Hapus Produk    | Menghapus produk dari toko, dengan atau tanpa konfirmasi  | Positive / Negative | Najwan |

---

## 👥 Contributors & Assignments

| Name     | Responsibility                          |
|----------|------------------------------------------|
| **Robbi**  | Registrasi, Update Toko                 |
| **Najwan** | Login, Hapus Produk                     |
| **Yogi**   | Cari Ayam, Tambah Karyawan              |
| **Marcel** | Tambah Keranjang, Pembayaran           |

---

## 📚 Documentation

Dokumentasi lengkap test suite tersedia dalam format **PDF** di dalam folder `src/test-suite` dan dikelola melalui **QASE.io**, sebuah test case management tools.
```lua
TA-PPPL/
├── src/
│ ├── main/ # Kode sumber utama
│ ├── test/ # Unit test & integration test
│ └── test-suite/ # Dokumentasi PDF dan file test suite
      │   ├── AVESTA-TEST SUITE PEMILIK TOKO.pdf
      │   └── AVESTA-TEST SUITE AUTHENTICATION.pdf
      │   └── AVESTA-TEST SUITE PEMBELI.pdf
```



🧁 Made with ❤️ by the Avesta Team
