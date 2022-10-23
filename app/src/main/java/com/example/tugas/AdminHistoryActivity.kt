package com.example.tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class AdminHistoryActivity : AppCompatActivity() {

    lateinit var listHistory: ListView
    lateinit var btKembali: Button
    lateinit var adapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_history)

        listHistory = findViewById(R.id.listHistory)
        btKembali = findViewById(R.id.btKembali_history)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, History.listHistory)
        listHistory.adapter = adapter

        btKembali.setBackgroundColor(resources.getColor(R.color.red))
        btKembali.setOnClickListener {
            finish()
        }
    }
}