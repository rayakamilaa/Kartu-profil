package com.example.belajarmobile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// VERSI AKHIR Latihan 5-6 (Modul Praktikum Pertemuan 6):
// menerima extra dari MainActivity, menampilkannya, dan menyediakan
// tombol Kembali (finish()). Jangan lupa: di AndroidManifest.xml,
// SecondActivity sekarang android:exported="false" (Latihan 7).
class SecondActivity : AppCompatActivity() {

    companion object {
        // Kunci extra — dipakai bersama oleh pengirim (MainActivity)
        // dan penerima (kelas ini) agar tidak salah ketik.
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_UMUR = "extra_umur"
        const val EXTRA_KATEGORI = "extra_kategori"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvNamaDiterima = findViewById<TextView>(R.id.tvNamaDiterima)
        val tvEmailDiterima = findViewById<TextView>(R.id.tvEmailDiterima)
        val tvUmurKategoriDiterima = findViewById<TextView>(R.id.tvUmurKategoriDiterima)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        // Latihan 5: ambil data dari Intent. getStringExtra bisa null
        // bila kunci tidak ditemukan — siapkan teks cadangan dengan ?:.
        val nama = intent.getStringExtra(EXTRA_NAMA)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val umur = intent.getIntExtra(EXTRA_UMUR, -1)
        val kategori = intent.getStringExtra(EXTRA_KATEGORI)

        tvNamaDiterima.text = nama ?: "(Nama tidak terkirim)"
        tvEmailDiterima.text = email ?: "(Email tidak terkirim)"
        
        if (umur >= 0 && !kategori.isNullOrEmpty()) {
            tvUmurKategoriDiterima.text = "$umur Tahun • $kategori"
        } else {
            tvUmurKategoriDiterima.text = "Data Umur Belum Tersedia"
        }

        // Latihan 6: tombol Kembali menutup layar ini.
        btnKembali.setOnClickListener {
            finish()
        }
    }
}
