package com.example.tugas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView

class CustomerHomepageActivity : AppCompatActivity() {

    lateinit var tvName: TextView
    lateinit var etCariNama: EditText
    lateinit var jenisSpinner: Spinner
    lateinit var listBeli: ListView
    lateinit var tvTotalBiaya: TextView
    lateinit var btBeli: Button

    lateinit var user: User

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_homepage)

        tvName = findViewById(R.id.tvName)
        etCariNama = findViewById(R.id.etCariNama)
        jenisSpinner = findViewById(R.id.jenisSpinner)
        listBeli = findViewById(R.id.listBeli)
        tvTotalBiaya = findViewById(R.id.tvTotalBiaya)
        btBeli = findViewById(R.id.btBeli)

        val idx = intent.getIntExtra("idxUser", 0)
        user = User.listUser[idx]

        tvName.text = "Hi, ${user.name} !"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        menu!!.findItem(R.id.tambah).isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history -> {
                val intent = Intent(this, CustomerHistoryActivity::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}