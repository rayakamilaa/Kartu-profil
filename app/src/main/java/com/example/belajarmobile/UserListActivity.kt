package com.example.belajarmobile

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Pertemuan 10 â€” Latihan 4 & 6: UserListActivity
// Activity yang menampilkan daftar pengguna dari API JSONPlaceholder.
// Menangani tiga state: loading, sukses, dan error.
class UserListActivity : AppCompatActivity() {

    private lateinit var rvUsers: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvStatusUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        rvUsers = findViewById(R.id.rvUsers)
        progressBar = findViewById(R.id.progressBar)
        tvStatusUser = findViewById(R.id.tvStatusUser)

        // Siapkan RecyclerView dengan LayoutManager
        rvUsers.layoutManager = LinearLayoutManager(this)

        // --- Latihan 6: State Loading ---
        progressBar.visibility = View.VISIBLE
        tvStatusUser.text = "Memuat data pengguna..."

        // --- Latihan 4: enqueue Callback ---
        RetrofitClient.api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()

                    // --- Latihan 6: State Sukses ---
                    progressBar.visibility = View.GONE
                    tvStatusUser.text = "" // Kosongkan status setelah data tampil
                    rvUsers.adapter = UserAdapter(users)
                } else {
                    // Server menjawab tapi bukan 2xx (misalnya 404, 500)
                    progressBar.visibility = View.GONE
                    tvStatusUser.text = "Gagal memuat data: kode ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // --- Latihan 6: State Error ---
                // onFailure dipanggil saat gagal jaringan (tidak ada internet, server mati)
                progressBar.visibility = View.GONE
                tvStatusUser.text = "Gagal memuat data: ${t.message}"
                Toast.makeText(this@UserListActivity, "Periksa koneksi internet", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
