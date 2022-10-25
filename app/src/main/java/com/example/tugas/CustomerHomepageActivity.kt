package com.example.tugas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.text.NumberFormat
import java.util.*

class CustomerHomepageActivity : AppCompatActivity() {

    lateinit var tvName: TextView
    lateinit var etCariNama: EditText
    lateinit var jenisSpinner: Spinner
    lateinit var listBeli: ListView
    lateinit var tvTotalBiaya: TextView
    lateinit var btBeli: Button

    lateinit var user: User
    lateinit var obatAdapter: ObatAdapter
//    lateinit var adapter: ArrayAdapter<*>
    var filterObat: ArrayList<Obat> = arrayListOf()
    var jumlahObat = ArrayList<Int>()
    var totalBiaya = 0

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

        filterObat.addAll(Obat.listObat)
        for (i in 0 until filterObat.size) {
            jumlahObat.add(0)
        }

        obatAdapter = ObatAdapter(this, filterObat, jumlahObat) {
            jumlahObat ->
            this.jumlahObat = jumlahObat
            totalBiaya = 0
            for (i in 0 until jumlahObat.size) {
                totalBiaya += jumlahObat[i] * filterObat[i].harga
            }
            val locale = Locale("id", "ID")
            val number = NumberFormat.getCurrencyInstance(locale)
            tvTotalBiaya.text = "Total Biaya : ${number.format(totalBiaya)},00"
        }
        listBeli.adapter = obatAdapter
        obatAdapter.notifyDataSetChanged()

        etCariNama.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                filterObat.clear()
                filterObat.addAll(filterSearch())
                jumlahObat.clear()
                for (i in 0 until filterObat.size) {
                    jumlahObat.add(0)
                }
                obatAdapter.notifyDataSetChanged()
            }
        })

        jenisSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                filterObat.clear()
                filterObat.addAll(filterSearch())
                jumlahObat.clear()
                for (i in 0 until filterObat.size) {
                    jumlahObat.add(0)
                }
                obatAdapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btBeli.setOnClickListener {
            var totalObat = 0
            for (i in 0 until jumlahObat.size) {
                totalObat += jumlahObat[i]
            }
            user.history.add(History("${user.history.size + 1}", totalBiaya, totalObat))
            for (i in 0 until jumlahObat.size) {
                jumlahObat[i] = 0
            }
            totalBiaya = 0
            tvTotalBiaya.text = "Total Biaya : Rp0,00"
            obatAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Berhasil beli!", Toast.LENGTH_SHORT).show()
        }
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

    private fun filterSearch(): ArrayList<Obat> {
        if (etCariNama.text.isNotBlank()) {
            if (jenisSpinner.selectedItemPosition != 0) {
                return Obat.listObat.filter { s -> s.nama.contains(etCariNama.text.toString(), ignoreCase = true)  && s.jenis == jenisSpinner.selectedItem.toString()} as ArrayList<Obat>
            }
            return Obat.listObat.filter { s -> s.nama.contains(etCariNama.text.toString(), ignoreCase = true)} as ArrayList<Obat>
        } else {
            if (jenisSpinner.selectedItemPosition != 0) {
                return Obat.listObat.filter { s -> s.jenis == jenisSpinner.selectedItem.toString()} as ArrayList<Obat>
            }
        }
        return Obat.listObat
    }
}