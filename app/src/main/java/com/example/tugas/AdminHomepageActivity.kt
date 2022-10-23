package com.example.tugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class AdminHomepageActivity : AppCompatActivity() {

    lateinit var listObat: ListView
    lateinit var adapter: ArrayAdapter<*>
    lateinit var adminAction: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_homepage)

        listObat = findViewById(R.id.listObat)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Obat.listObat)
        listObat.adapter = adapter

        adminAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            res: ActivityResult ->
            if (res.resultCode == RESULT_OK) {
                val data = res.data
                if (data != null) {
                    adapter.notifyDataSetChanged()
                }
            }
        }

        listObat.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val idx = listObat.getItemIdAtPosition(position).toInt()

                //POPUP MENU
                val popUp = PopupMenu(this, view)
                popUp.menuInflater.inflate(R.menu.context_menu, popUp.menu)
                popUp.setOnMenuItemClickListener {
                    return@setOnMenuItemClickListener when(it.itemId) {
                        R.id.ubah -> {
                            val intent = Intent(this, AdminObatActivity::class.java)
                            intent.putExtra("idx", idx)
                            adminAction.launch(intent)
                            true
                        }
                        R.id.delete -> {
                            Obat.listObat.removeAt(idx)
                            Toast.makeText(this, "Berhasil menghapus obat!", Toast.LENGTH_SHORT).show()
                            adapter.notifyDataSetChanged()
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
                popUp.show()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tambah -> {
                val intent = Intent(this, AdminObatActivity::class.java)
                adminAction.launch(intent)
            }
            R.id.history -> {
                val intent = Intent(this, AdminHistoryActivity::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}