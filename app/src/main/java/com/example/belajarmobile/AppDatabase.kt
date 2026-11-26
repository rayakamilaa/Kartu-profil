package com.example.belajarmobile

import androidx.room.Database
import androidx.room.RoomDatabase

// Latihan 4 — Modul Praktikum Pertemuan 9 (kerangka Room).
// DATABASE: penghubung antara Entity dan Dao.
// Untuk pertemuan ini cukup sampai kerangka tersusun dan bisa
// dijelaskan per bagiannya. Pemanggilan tambah() dan semua() dari
// tombol dipraktikkan penuh di Pertemuan 11, setelah coroutine
// dipelajari.
//
// Perhatian: bila versi database diubah tanpa strategi migrasi,
// aplikasi crash saat dibuka. Selama belajar, naikkan `version`
// dengan hati-hati dan hapus data aplikasi bila perlu — tapi catat
// bahwa di aplikasi nyata, migrasi harus direncanakan.
@Database(entities = [Catatan::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catatanDao(): CatatanDao
}
