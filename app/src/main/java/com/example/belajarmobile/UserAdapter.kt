package com.example.belajarmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Pertemuan 10 â€” Latihan 5: UserAdapter
// Penghubung antara List<User> dan RecyclerView.
// Adapter dipasang SETELAH data datang di onResponse â€” bukan di onCreate.
class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder memegang ketiga TextView satu baris.
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaUser: TextView = itemView.findViewById(R.id.tvNamaUser)
        val tvEmailUser: TextView = itemView.findViewById(R.id.tvEmailUser)
        val tvUsernameUser: TextView = itemView.findViewById(R.id.tvUsernameUser)
    }

    // Dipanggil RecyclerView saat butuh baris baru â€” inflate layout item_user.xml.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    // Dipanggil untuk tiap baris yang terlihat â€” isi TextView dengan data user.
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.tvNamaUser.text = user.name
        holder.tvEmailUser.text = user.email
        holder.tvUsernameUser.text = "@" + user.username
    }

    // Jumlah baris = jumlah user. Bila 0, RecyclerView menampilkan nothing.
    override fun getItemCount(): Int = users.size
}
