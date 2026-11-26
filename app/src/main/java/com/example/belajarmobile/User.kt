package com.example.belajarmobile

// Pertemuan 10 â€” Latihan 2: Model User
// Data class yang memetakan JSON dari API JSONPlaceholder.
// Nama field HARUS sama persis dengan kunci JSON (id, name, username, email, phone, website).
// Gson akan mencocokkan berdasarkan nama field ini.
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)
