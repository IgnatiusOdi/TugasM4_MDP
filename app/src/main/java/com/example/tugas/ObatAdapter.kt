package com.example.tugas

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ObatAdapter(
    private val context: Activity,
) : ArrayAdapter<Obat>(context, R.layout.obat_list) {
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = context.layoutInflater.inflate(R.layout.obat_list, null, true)

        val tvNama = view.findViewById<TextView>(R.id.tvNamaObat)
        val tvStok = view.findViewById<TextView>(R.id.tvStokObat)
        val tvHarga = view.findViewById<TextView>(R.id.tvHargaObat)
        val tvSubtotal = view.findViewById<TextView>(R.id.tvSubtotalObat)
        val btMinus = view.findViewById<Button>(R.id.btMinus)
        val btPlus = view.findViewById<Button>(R.id.btPlus)

        tvNama.text = Obat.listObat[position].nama
        tvStok.text = "0"
        tvHarga.text = "Rp. ${Obat.listObat[position].harga},00"
        tvSubtotal.text = "Rp. 0,00"

        btMinus.setOnClickListener {
            Toast.makeText(context, "Minus", Toast.LENGTH_SHORT).show()
        }
        
        btPlus.setOnClickListener {
            Toast.makeText(context, "Plus", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}