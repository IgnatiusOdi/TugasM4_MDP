package com.example.tugas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class AdminObatActivity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var etNama: EditText
    lateinit var etHarga: EditText
    lateinit var etStok: EditText
    lateinit var rbBubuk: RadioButton
    lateinit var rbCair: RadioButton
    lateinit var rbTablet: RadioButton
    lateinit var rbKapsul: RadioButton
    lateinit var btKembali: Button
    lateinit var btTambah: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_obat)

        tvTitle = findViewById(R.id.tvTitle)
        etNama = findViewById(R.id.etNama)
        etHarga = findViewById(R.id.etHarga)
        etStok = findViewById(R.id.etStok)
        rbBubuk = findViewById(R.id.rbBubuk)
        rbCair = findViewById(R.id.rbCair)
        rbTablet = findViewById(R.id.rbTablet)
        rbKapsul = findViewById(R.id.rbKapsul)
        btKembali = findViewById(R.id.btKembali)
        btTambah = findViewById(R.id.btAction)

        val idx = intent.getIntExtra("idx", -1)
        if (idx != -1) {
            tvTitle.text = "Edit Obat"
            etNama.setText(Obat.listObat[idx].nama)
            etHarga.setText(Obat.listObat[idx].harga.toString())
            etStok.setText(Obat.listObat[idx].stok.toString())
            when (Obat.listObat[idx].jenis) {
                "Bubuk" -> {
                    rbBubuk.isChecked = true
                }
                "Cair" -> {
                    rbCair.isChecked = true
                }
                "Tablet" -> {
                    rbTablet.isChecked = true
                }
                else -> {
                    rbKapsul.isChecked = true
                }
            }
            btTambah.text = "SIMPAN"
        }

        btKembali.setBackgroundColor(resources.getColor(R.color.red))
        btKembali.setOnClickListener {
            finish()
        }

        btTambah.setOnClickListener {
            if (etNama.text.isBlank() || etHarga.text.isBlank() || etStok.text.isBlank() || (!rbBubuk.isChecked && !rbCair.isChecked && !rbTablet.isChecked && !rbKapsul.isChecked)) {
                Toast.makeText(this, "Field tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val jenis = if (rbBubuk.isChecked) {
                    "Bubuk"
                } else if (rbCair.isChecked) {
                    "Cair"
                } else if (rbTablet.isChecked) {
                    "Tablet"
                } else {
                    "Kapsul"
                }
                
                if (idx != -1) {
                    Obat.listObat[idx] = Obat(etNama.text.toString(), etHarga.text.toString().toInt(), etStok.text.toString().toInt(), jenis)
                    Toast.makeText(this, "Berhasil mengedit obat!", Toast.LENGTH_SHORT).show()
                } else {
                    Obat.listObat.add(Obat(etNama.text.toString(), etHarga.text.toString().toInt(), etStok.text.toString().toInt(), jenis))
                    Toast.makeText(this, "Berhasil menambahkan obat!", Toast.LENGTH_SHORT).show()
                }

                setResult(RESULT_OK, Intent())
                finish()
            }
        }

    }
}