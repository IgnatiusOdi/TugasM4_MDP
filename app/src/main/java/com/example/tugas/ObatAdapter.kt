package com.example.tugas

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ObatAdapter(
    private val context: Activity,
    private val listObat: ArrayList<Obat>,
    private val jumlahObat: ArrayList<Int>,
    private val onItemClickListener: (jumlahObat: ArrayList<Int>) -> Unit,
) : ArrayAdapter<Int>(context, R.layout.obat_list, jumlahObat) {
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = context.layoutInflater.inflate(R.layout.obat_list, null, true)

        val tvNama = view.findViewById<TextView>(R.id.tvNamaObat)
        val tvStok = view.findViewById<TextView>(R.id.tvStokObat)
        val tvHarga = view.findViewById<TextView>(R.id.tvHargaObat)
        val tvSubtotal = view.findViewById<TextView>(R.id.tvSubtotalObat)
        val btMinus = view.findViewById<Button>(R.id.btMinus)
        val btPlus = view.findViewById<Button>(R.id.btPlus)

        val locale = Locale("id", "ID")
        val number = NumberFormat.getCurrencyInstance(locale)

        tvNama.text = listObat[position].nama
        tvStok.text = "0"
        tvHarga.text = "${number.format(listObat[position].harga)},00"
        tvSubtotal.text = "Rp0,00"

        btMinus.setOnClickListener {
            if (jumlahObat[position] - 1 < 0) {
                Toast.makeText(context, "Tidak boleh kurang dari 0!", Toast.LENGTH_SHORT).show()
            } else {
                jumlahObat[position] -= 1
                tvStok.text = jumlahObat[position].toString()
                tvSubtotal.text = "${number.format(listObat[position].harga * jumlahObat[position])},00"
                onItemClickListener(jumlahObat)
            }
        }
        
        btPlus.setOnClickListener {
            if (jumlahObat[position] + 1 > listObat[position].stok) {
                Toast.makeText(context, "Stok tidak mencukupi!", Toast.LENGTH_SHORT).show()
            } else {
                jumlahObat[position] += 1
                tvStok.text = jumlahObat[position].toString()
                tvSubtotal.text = "${number.format(listObat[position].harga * jumlahObat[position])},00"
                onItemClickListener(jumlahObat)
            }
        }

        return view
    }
}