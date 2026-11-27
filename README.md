# Proyek Kartu Profil — Pertemuan 9 & 10 (Tema Spider-Man UI)

Aplikasi Android **Kartu Profil** (`com.example.belajarmobile`) yang dikembangkan menggunakan **Kotlin**, **XML Layout**, **Material Design Components**, dan **Android Studio** berbasis **Gradle**. Pada pengembangan Pertemuan 9 dan 10, proyek ini dilengkapi dengan tampilan visual *Glassmorphic Card* bertema **Spider-Man**, penyimpanan data lokal persisten (**SharedPreferences** dan **Database Room**), serta fitur pengambilan daftar pengguna dari internet secara langsung menggunakan **REST API** (**Retrofit** + **Gson** + **RecyclerView**).

---

## 🛠️ Teknologi & Komponen Utama

- **Bahasa & UI:** Kotlin, Material Design Components 3, XML Layout (`activity_main.xml`, `activity_second.xml`, `activity_user_list.xml`, `item_user.xml`), serta Custom Drawables (`bg_spiderman.jpg`, `img_profile.jpg`, `bg_form_card.xml`, `bg_input_field.xml`).
- **Penyimpanan Lokal:**
  - **SharedPreferences:** Menyimpan dan memuat otomatis data profil terakhir (Nama, Email, Tahun Lahir, Umur, dan Kategori) saat aplikasi dibuka kembali.
  - **Room Database:** Kerangka database lokal persisten berbasis SQLite (`AppDatabase.kt`, `CatatanDao.kt`, dan entitas `Catatan.kt`).
- **Jaringan & Daftar Data:** Retrofit 2, Converter Gson, dan RecyclerView (`ApiService.kt`, `User.kt`, `UserAdapter.kt`, `UserListActivity.kt`).
- **Lingkungan Pengembangan:** Android Studio (Gradle Build System).

---

## 🚀 Fitur Utama & Alur Pengujian

1. **Form Profil, Validasi & Perhitungan Usia:**
   - Mengisi data diri (**Nama Lengkap**, **Alamat Email**, **Tahun Lahir**).
   - Validasi berlapis (Email harus valid memuat `@` dan `.`, Tahun Lahir angka 1950–tahun saat ini).
   - Perhitungan umur otomatis beserta kategori usia (*Anak*, *Remaja*, *Dewasa*, *Lansia*).
   - Tombol **Batal** mengosongkan kolom input & tanda error.
   - Tombol **Hapus Data** menghapus penyimpanan lokal `SharedPreferences` dan mengosongkan formulir.
   - Tombol **Simpan** menyimpan data lokal dan membawa data olahan ke layar **Tentang Saya** (`SecondActivity`).
2. **Muat Otomatis Saat Buka Ulang Aplikasi:**
   - Saat aplikasi ditutup lalu dibuka kembali, seluruh kolom input (Nama, Email, Tahun Lahir) otomatis terisi kembali dengan data profil terakhir yang tersimpan.
3. **Halaman Kedua — Tentang Saya (`SecondActivity`):**
   - Tampilan estetik rata tengah (*center aligned*) berdesain kartu transparan dengan foto profil berbentuk lingkaran sempurna dan badge kategori umur.
4. **Daftar Pengguna REST API (`UserListActivity`):**
   - Membuka halaman Daftar Pengguna melalui tombol **Lihat Pengguna**.
   - Mengambil data dari endpoint `https://jsonplaceholder.typicode.com/users` menggunakan **Retrofit + Gson** dan menampilkannya dalam **RecyclerView**.
5. **Penanganan Kondisi Online & Offline:**
   - **Online:** Menampilkan daftar pengguna (*Leanne Graham*, *Ervin Howell*, dst.) secara responsif.
   - **Offline / Mode Pesawat:** Menampilkan pesan kegagalan memuat di teks status dan pesan error tanpa mengalami *crash*.

---

## 📸 Dokumentasi Tangkapan Layar (Screenshots)

## 📸 Dokumentasi Tangkapan Layar (Screenshots)

| No | Tampilan | Preview | Keterangan |
| :---: | :--- | :---: | :--- |
| **1** | **Tampilan Awal Form Profil (Kosong)** | <img src="https://github.com/user-attachments/assets/c2c6f39f-4e19-439b-8e82-43306765058b" width="210" alt="Tampilan Awal Form Profil"> | Halaman utama (`activity_main.xml`) dengan input Nama, Email, dan Tahun Lahir kosong. |
| **2** | **Pengisian Form Data Diri** | <img src="https://github.com/user-attachments/assets/3bfed0b9-13be-4ee8-bd1c-b7cade798132" width="210" alt="Form Data Diri Terisi"> | Form data diri setelah Nama, Email, dan Tahun Lahir diisi lengkap. |
| **3** | **Halaman Kedua — Tentang Saya** | <img src="https://github.com/user-attachments/assets/8dfd0116-49ee-4f96-8bd6-95d28ddd188b" width="210" alt="Halaman Tentang Saya"> | Tampilan `SecondActivity` rata tengah dengan foto lingkaran, identitas, dan badge kategori umur. |
| **4** | **Data Tersimpan Saat Aplikasi Dibuka Ulang** | <img src="https://github.com/user-attachments/assets/dc413f6e-5893-41bb-a956-c233aa7e75b5" width="210" alt="Data Tersimpan Auto Load"> | Data profil terakhir otomatis dimuat ulang saat aplikasi dibuka kembali. |
| **5** | **Tampilan Profil dengan Tombol Lihat Pengguna** | <img src="https://github.com/user-attachments/assets/13013cbf-5da2-4a94-bd4e-35a26106e4bd" width="210" alt="Profil dengan Tombol Lihat Pengguna"> | Halaman utama lengkap dengan tombol aksi termasuk **Lihat Pengguna**. |
| **6** | **Daftar Pengguna (Mode Online)** | <img src="https://github.com/user-attachments/assets/9f44af2b-681d-4e99-bbe5-91bd2bdcbd83" width="210" alt="Daftar Pengguna Online"> | `UserListActivity` menampilkan daftar pengguna dari REST API saat online. |
| **7** | **Daftar Pengguna (Mode Offline / Tanpa Koneksi)** | <img src="https://github.com/user-attachments/assets/047da58a-14fb-43e5-a785-20c975b8b6cc" width="210" alt="Daftar Pengguna Offline Error"> | `UserListActivity` menampilkan pesan gagal memuat data saat offline/mode pesawat. |

---

# 💾 Implementasi & Penerapan Penyimpanan Lokal di Proyek Ini

Pada proyek ini digunakan 2 mekanisme penyimpanan data lokal sesuai kebutuhan data yang dikelola:

### 1. SharedPreferences (`PREFS_PROFIL = "profil"`)
*   **Penerapan di Kode (`MainActivity.kt`):** 
    Digunakan khusus untuk menyimpan **1 set data profil pengguna terakhir**.
    *   **Kunci Penyimpanan:** `KEY_NAMA_TERAKHIR`, `KEY_EMAIL_TERAKHIR`, `KEY_TAHUN_LAHIR_TERAKHIR`, `KEY_UMUR_TERAKHIR`, dan `KEY_KATEGORI_TERAKHIR`.
    *   **Saat Tombol Simpan Diklik:** Menyimpan input formulir secara permanen menggunakan `prefs.edit().putString(...).apply()`.
    *   **Saat Aplikasi Dibuka Kembali (`onCreate`):** Membaca kunci `SharedPreferences`. Jika data ditemukan, kolom input `etNama`, `etEmail`, dan `etTahunLahir` otomatis terisi kembali.
    *   **Saat Tombol Hapus Data Diklik:** Memanggil `prefs.edit().clear().apply()` untuk menghapus berkas penyimpanan profil dan mengosongkan layar.

### 2. Room Database (`AppDatabase.kt`, `CatatanDao.kt`, `Catatan.kt`)
*   **Penerapan di Kode:**
    Disiapkan sebagai kerangka database SQLite terstruktur untuk mengelola **data banyak entri (catatan)** secara persisten.
    *   **Entitas (`Catatan.kt`):** Mendefinisikan tabel SQL `"catatan"` dengan kolom `id` (Auto Increment), `judul`, dan `isi`.
    *   **DAO (`CatatanDao.kt`):** Antarmuka Data Access Object yang menyediakan fungsi operasi database berbasis coroutine (`suspend`):
        *   `tambah(catatan: Catatan)` ➔ Menambahkan baris catatan baru.
        *   `semua()` ➔ Mengambil seluruh daftar catatan diurutkan dari ID terbaru (`SELECT * FROM catatan ORDER BY id DESC`).
    *   **Database (`AppDatabase.kt`):** Mengosongkan konfigurasi `RoomDatabase` versi 1 yang menghubungkan entitas `Catatan` dengan `CatatanDao`.

---


## 💻 Cara Menjalankan Aplikasi

1. Buka folder proyek (`proyek-kartu-profil-p4-p6`) di **Android Studio**.
2. Tunggu proses **Gradle Sync** selesai hingga tidak ada error konfigurasi.
3. Hubungkan HP Android (dengan *USB Debugging* aktif) atau jalankan **Emulator** di Android Studio.
4. Klik tombol **Run** (ikon play hijau `Shift + F10`) di Android Studio untuk membangun (*build*) dan memasang aplikasi ke perangkat.
