package com.example.tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView

class AdminHistory : AppCompatActivity() {

    lateinit var listHistory: ListView
    lateinit var btKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_history)

        listHistory = findViewById(R.id.listHistory)
        btKembali = findViewById(R.id.btKembali_history)

        btKembali.setBackgroundColor(resources.getColor(R.color.red))
        btKembali.setOnClickListener {
            finish()
        }
    }
}