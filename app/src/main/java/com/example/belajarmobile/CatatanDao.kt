package com.example.belajarmobile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// Latihan 4 — Modul Praktikum Pertemuan 9 (kerangka Room).
// DAO: perintah simpan, baca, dan hapus untuk tabel "catatan".
// Fungsinya memakai `suspend`: mengambil data dari database adalah
// pekerjaan yang butuh waktu, dan di Pertemuan 11 fungsi seperti ini
// dipanggil dari dalam coroutine agar layar tidak membeku.
@Dao
interface CatatanDao {
    @Insert
    suspend fun tambah(catatan: Catatan)

    @Query("SELECT * FROM catatan ORDER BY id DESC")
    suspend fun semua(): List<Catatan>
}
