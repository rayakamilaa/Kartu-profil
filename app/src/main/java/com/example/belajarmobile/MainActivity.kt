package com.example.belajarmobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

// VERSI AKHIR Pertemuan 9 — Penyimpanan Data Lokal: SharedPreferences
// dan Room Dasar. Lanjutan langsung dari versi akhir Pertemuan 7:
// semua validasi berlapis, perhitungan umur, dan kategori dengan when
// tetap sama. Yang baru: nama, umur, dan kategori terakhir DISIMPAN
// dengan SharedPreferences setelah validasi lolos, lalu DIBACA kembali
// saat aplikasi dibuka — aplikasi kini "mengingat". Kerangka Room
// (Entity, Dao, Database) ada di file terpisah (Catatan.kt,
// CatatanDao.kt, AppDatabase.kt) sebagai bekal Pertemuan 11.
class MainActivity : AppCompatActivity() {

    companion object {
        // Kunci SharedPreferences — dipakai bersama saat menyimpan
        // dan membaca, supaya ejaannya tidak berbeda di dua tempat.
        const val PREFS_PROFIL = "profil"
        const val KEY_NAMA_TERAKHIR = "nama_terakhir"
        const val KEY_EMAIL_TERAKHIR = "email_terakhir"
        const val KEY_TAHUN_LAHIR_TERAKHIR = "tahun_lahir_terakhir"
        const val KEY_UMUR_TERAKHIR = "umur_terakhir"
        const val KEY_KATEGORI_TERAKHIR = "kategori_terakhir"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTahunLahir = findViewById<EditText>(R.id.etTahunLahir)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnBatal = findViewById<Button>(R.id.btnBatal)
        val btnHapusSimpan = findViewById<Button>(R.id.btnHapusSimpan)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val btnLihatPengguna = findViewById<Button>(R.id.btnLihatPengguna)

        // Pertemuan 10 — Latihan 6: tombol untuk membuka daftar pengguna dari API
        btnLihatPengguna.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }

        // --- Pertemuan 9, Latihan 2: baca kembali data tersimpan. ---
        val prefs = getSharedPreferences(PREFS_PROFIL, Context.MODE_PRIVATE)
        val namaTersimpan = prefs.getString(KEY_NAMA_TERAKHIR, "") ?: ""
        val emailTersimpan = prefs.getString(KEY_EMAIL_TERAKHIR, "") ?: ""
        val tahunLahirTersimpan = prefs.getInt(KEY_TAHUN_LAHIR_TERAKHIR, -1)
        val umurTersimpan = prefs.getInt(KEY_UMUR_TERAKHIR, -1)
        val kategoriTersimpan = prefs.getString(KEY_KATEGORI_TERAKHIR, "") ?: ""

        // Isi kembali semua field form jika data pernah disimpan sebelumnya
        if (namaTersimpan.isNotEmpty()) {
            etNama.setText(namaTersimpan)
        }
        if (emailTersimpan.isNotEmpty()) {
            etEmail.setText(emailTersimpan)
        }
        if (tahunLahirTersimpan > 0) {
            etTahunLahir.setText(tahunLahirTersimpan.toString())
        }
        if (namaTersimpan.isNotEmpty() && umurTersimpan >= 0 && kategoriTersimpan.isNotEmpty()) {
            tvStatus.text = "Terakhir tersimpan: $namaTersimpan, $umurTersimpan tahun • $kategoriTersimpan"
        }

        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val teksTahun = etTahunLahir.text.toString().trim()

            // --- Latihan 1: validasi berlapis, dari kolom pertama. ---
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
                etNama.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            // Pemeriksaan format sederhana: harus ada "@" dan "." sesudahnya.
            if (!email.contains("@") || !email.contains(".")) {
                etEmail.error = "Format email belum benar (contoh: nama@mail.com)"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            // --- Latihan 2: konversi aman + validasi angka. ---
            val tahunLahir = teksTahun.toIntOrNull()
            if (tahunLahir == null) {
                etTahunLahir.error = "Tahun lahir harus berupa angka, mis. 2004"
                etTahunLahir.requestFocus()
                return@setOnClickListener
            }
            val tahunSekarang = Calendar.getInstance().get(Calendar.YEAR)
            if (tahunLahir < 1950 || tahunLahir > tahunSekarang) {
                etTahunLahir.error = "Tahun lahir harus antara 1950 dan $tahunSekarang"
                etTahunLahir.requestFocus()
                return@setOnClickListener
            }

            // --- Latihan 3: logika perhitungan + kategori dengan when. ---
            val umur = tahunSekarang - tahunLahir
            val kategori = when (umur) {
                in 0..12 -> "Anak"
                in 13..17 -> "Remaja"
                in 18..59 -> "Dewasa"
                else -> "Lansia"
            }

            // --- Simpan SEMUA data (nama, email, tahun lahir, umur, kategori) ke SharedPreferences ---
            prefs.edit()
                .putString(KEY_NAMA_TERAKHIR, nama)
                .putString(KEY_EMAIL_TERAKHIR, email)
                .putInt(KEY_TAHUN_LAHIR_TERAKHIR, tahunLahir)
                .putInt(KEY_UMUR_TERAKHIR, umur)
                .putString(KEY_KATEGORI_TERAKHIR, kategori)
                .apply()

            tvStatus.text = "Profil disimpan: $nama"
            Toast.makeText(this, "Profil berhasil disimpan!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(SecondActivity.EXTRA_NAMA, nama)
            intent.putExtra(SecondActivity.EXTRA_EMAIL, email)
            intent.putExtra(SecondActivity.EXTRA_UMUR, umur)
            intent.putExtra(SecondActivity.EXTRA_KATEGORI, kategori)
            startActivity(intent)
        }

        // Klik Batal -> kosongkan formulir, termasuk tanda error.
        btnBatal.setOnClickListener {
            etNama.text.clear()
            etEmail.text.clear()
            etTahunLahir.text.clear()
            etNama.error = null
            etEmail.error = null
            etTahunLahir.error = null
            tvStatus.text = ""
            etNama.requestFocus()
            Toast.makeText(this, "Formulir dibersihkan", Toast.LENGTH_SHORT).show()
        }

        // --- Hapus semua data tersimpan di SharedPreferences & formulir ---
        btnHapusSimpan.setOnClickListener {
            prefs.edit().clear().apply()
            etNama.text.clear()
            etEmail.text.clear()
            etTahunLahir.text.clear()
            tvStatus.text = "Data tersimpan dihapus."
            Toast.makeText(this, "Data tersimpan berhasil dihapus", Toast.LENGTH_SHORT).show()
        }

        // Tekan lama tombol Simpan -> tampilkan petunjuk.
        btnSimpan.setOnLongClickListener {
            tvStatus.text = "Tekan biasa tombol Simpan untuk menyimpan & pindah layar"
            true
        }
    }
}
