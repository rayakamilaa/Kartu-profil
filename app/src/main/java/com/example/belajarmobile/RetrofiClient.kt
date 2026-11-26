package com.example.belajarmobile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Pertemuan 10 â€” Latihan 3: RetrofitClient Singleton
// Satu objek yang membangun Retrofit sekali dan dipakai di mana-mana.
// object + by lazy: dibangun sekali saat pertama dipakai, lalu dipakai berulang.
object RetrofitClient {
    // BASE_URL HARUS diakhiri garis miring (/). Tanpa itu, Retrofit crash
    // dengan IllegalArgumentException saat pertama dipakai.
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
