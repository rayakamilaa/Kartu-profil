package com.example.belajarmobile

import retrofit2.Call
import retrofit2.http.GET

// Pertemuan 10 — Latihan 2: ApiService
// Interface yang menjelaskan endpoint apa yang dipanggil.
// @GET("users") berarti: panggil endpoint "users" dengan method GET.
// Hasilnya daftar User — dieksekusi nanti dengan enqueue callback.
interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>
}
