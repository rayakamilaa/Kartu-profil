# Proyek Kartu Profil — Project Utuh Pertemuan 4–6

Project Android **lengkap dan berdiri sendiri** (gabungan final Pertemuan 4, 5,
dan 6 Pemrograman Mobile): aplikasi **Kartu Profil** yang sudah interaktif.

- Pertemuan 4: struktur project Android + Activity.
- Pertemuan 5: UI dasar (TextView, EditText, Button, ImageView, LinearLayout,
  ScrollView, resource strings/colors).
- Pertemuan 6: event handling & navigasi — tombol **Simpan** membaca nama &
  email, menampilkan Toast, lalu pindah ke layar kedua (`SecondActivity`)
  sambil membawa data lewat Intent `putExtra`; tombol **Batal** membersihkan
  kolom; tekan lama **Simpan** menampilkan petunjuk di teks status.

Package: `com.example.belajarmobile` • Min SDK 24 • Target/Compile SDK 34
Gradle 8.7 • Android Gradle Plugin 8.5.2 • Kotlin 2.0.20 • AppCompat 1.7.0

## Syarat di komputer yang dipakai

- **JDK 17** terpasang (cek: `java -version`).
- **Android SDK** terpasang (Command-line Tools + platform android-34).
- HP Android dengan USB debugging aktif — kabel USB **atau** adb wireless.

## Cara menjalankan

1. Ekstrak ZIP ini, lalu buka folder `proyek-kartu-profil-p4-p6` di VS Code
   (File → Open Folder). Pastikan folder yang dibuka adalah folder yang berisi
   `gradlew.bat` — bukan folder di dalamnya.
2. Hubungkan HP:
   - USB: colok kabel, ketuk **Izinkan** bila muncul dialog di HP.
   - Wireless (Android 11+): HP & laptop satu WiFi → Opsi Pengembang →
     Wireless debugging → `adb pair <IP:port-pairing>` lalu
     `adb connect <IP:port-koneksi>`.
3. Cek HP terbaca:

   ```
   adb devices
   ```

   Statusnya harus `device`.
4. Build & pasang dari terminal VS Code (PowerShell):

   ```powershell
   .\gradlew installDebug
   ```

   (Git Bash/macOS/Linux: `./gradlew installDebug`)
5. Aplikasi **Kartu Profil** terpasang di HP — buka dari daftar aplikasi.

> **Build pertama lebih lama** karena Gradle 8.7 dan semua dependency
> diunduh dulu — pastikan ada internet. Build berikutnya jauh lebih cepat.

## Bila muncul "SDK location not found"

Buat file `local.properties` di folder akar project (sejajar `gradlew.bat`)
berisi satu baris yang menunjuk folder SDK di komputer itu, contoh Windows:

```
sdk.dir=C\:\\Users\\NamaKamu\\AppData\\Local\\Android\\Sdk
```

Contoh Linux: `sdk.dir=/home/namakamu/Android/Sdk`

## Alur uji di HP

1. Isi nama dan email → tekan **Simpan** → muncul Toast, lalu pindah ke layar
   kedua yang menampilkan nama & email tadi.
2. Tekan **Kembali** (atau Back di HP) → kembali ke formulir.
3. Tekan **Batal** → kedua kolom kosong lagi.
4. Tekan **lama** tombol Simpan → petunjuk muncul di teks status.

Catatan: `SecondActivity` tidak bisa lagi dibuka lewat
`adb shell am start` seperti di Pertemuan 5 — memang disengaja karena
`android:exported="false"`; layar kedua hanya dibuka dari dalam aplikasi
lewat Intent.

## Troubleshooting ringkas

- `The term '.\gradlew' is not recognized` → terminal belum berada di folder
  akar project (yang ada `gradlew.bat`-nya). `cd` ke folder itu dulu.
- `No connected devices` → ulangi langkah 2–3; pada wireless, port berubah
  setiap Wireless debugging dinyalakan ulang.
- `Unsupported class file major version` → JDK yang terbaca bukan 17.
- Aplikasi crash → jalankan `adb logcat *:E`, cari baris `FATAL EXCEPTION`.
