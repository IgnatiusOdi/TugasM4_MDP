package com.example.tugas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    val username: String,
    val name: String,
    val password: String,
    val nomortelepon: String,
): Parcelable {
    companion object {
        var listUser = ArrayList<User>()
    }
}