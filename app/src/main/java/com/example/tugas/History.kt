package com.example.tugas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
class History (
    val nama: String,
    val total: Int,
    val item: Int,
): Parcelable {
    companion object {
        var listHistory = ArrayList<History>()
    }

    override fun toString(): String {
        val locale = Locale("id", "ID")
        val number = NumberFormat.getCurrencyInstance(locale)
        return "$nama - Rp. ${number.format(total)},00 - $item item"
    }
}