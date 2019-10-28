package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.names_row_layout.view.*

class NamesAdapter(private var users: List<User>) :
    RecyclerView.Adapter<NamesAdapter.NamesViewHolder>() {

    var data: List<User>
        get() = users
        set(value) {
            this.users = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder =
        LayoutInflater.from(parent.context).let {
            val view = it.inflate(R.layout.names_row_layout, parent, false)
            NamesViewHolder(view)
        }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.bind(users[position])
    }


    class NamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userText: TextView = itemView.nameText

        fun bind(user: User) {
            userText.text = user.userName()
        }
    }
}