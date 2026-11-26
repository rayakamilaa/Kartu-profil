package com.example.belajarmobile

import androidx.room.Entity
import androidx.room.PrimaryKey

// Latihan 4 — Modul Praktikum Pertemuan 9 (kerangka Room).
// ENTITY: satu baris tabel = satu objek Catatan.
// Tabel bernama "catatan", id naik otomatis tiap baris baru.
@Entity(tableName = "catatan")
data class Catatan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val isi: String
)
