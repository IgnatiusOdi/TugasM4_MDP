package com.example.tugas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.*

@Parcelize
class Obat(
    var nama: String,
    var harga: Int,
    var stok: Int,
    var jenis: String,
): Parcelable {
    companion object {
        var listObat = ArrayList<Obat>()
    }

    override fun toString(): String {
        val locale = Locale("id", "ID")
        val number = NumberFormat.getCurrencyInstance(locale)
        return "$nama ($jenis) [$stok] - ${number.format(harga)},00"
    }
}