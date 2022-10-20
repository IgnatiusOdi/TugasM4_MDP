package com.example.tugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView

class AdminHomepageActivity : AppCompatActivity() {

    lateinit var listObat: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_homepage)

        listObat = findViewById(R.id.listObat)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tambah -> {
                val intent = Intent(this, AdminObatActivity::class.java)
                startActivity(intent)
            }
            R.id.history -> {
                val intent = Intent(this, AdminHistory::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}