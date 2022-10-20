package com.example.tugas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

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
        val locale = Locale.getDefault()
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        return "$nama ($jenis) [$stok] - Rp. ${numberFormat.format(harga)}"
    }
}