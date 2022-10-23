package com.example.tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class CustomerHistoryActivity : AppCompatActivity() {

    lateinit var listHistory: ListView
    lateinit var btKembali: Button
    lateinit var adapter: ArrayAdapter<*>

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_history)

        listHistory = findViewById(R.id.listHistory_customer)
        btKembali = findViewById(R.id.btKembali_customer)

        val idx = intent.getIntExtra("idxUser", 0)
        user = User.listUser[idx]

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, user.history)
        listHistory.adapter = adapter

        btKembali.setBackgroundColor(resources.getColor(R.color.red))
        btKembali.setOnClickListener {
            finish()
        }
    }
}