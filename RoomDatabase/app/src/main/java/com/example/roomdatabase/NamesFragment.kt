package com.example.roomdatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_names.*

class NamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_names, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        names.apply {
            adapter = NamesAdapter(AppDatabase.getInstance(context).userDao().getAll())
            layoutManager = LinearLayoutManager(activity)
        }

        submitButton.setOnClickListener {
            activity?.let { context ->
                AppDatabase.getInstance(context).userDao().insertAll(
                    User(
                        firstNameText.text.toString(), lastNameText.text.toString()
                    )
                )

                (names.adapter as? NamesAdapter)?.let {
                    it.data = AppDatabase.getInstance(context).userDao().getAll()
                }
            }
        }
    }
}
