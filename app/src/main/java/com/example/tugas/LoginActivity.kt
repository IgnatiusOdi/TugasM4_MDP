package com.example.tugas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btLogin: Button
    lateinit var btToRegister: Button

    private var indexUser = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsernameLogin)
        etPassword = findViewById(R.id.etPasswordLogin)
        btLogin = findViewById(R.id.btLogin)
        btToRegister = findViewById(R.id.btToRegister)

        //DEBUG
        User.listUser.add(User("a", "David", "a", "12345678"))
        User.listUser[0].history.add(History("# 1", 188000, 3))
        User.listUser[0].history.add(History("2", 96000, 4))
        User.listUser[0].history.add(History("3", 52000, 2))
        etUsername.setText("a")
        etPassword.setText("a")
        Obat.listObat.add(Obat("OBH Cair", 16000, 5, "Cair"))
        Obat.listObat.add(Obat("Tolak Angin", 3000, 4, "Cair"))
        Obat.listObat.add(Obat("Panadol Merah", 12000, 2, "Tablet"))
        Obat.listObat.add(Obat("Panadol Hijau", 15000, 10, "Tablet"))
        Obat.listObat.add(Obat("Panadol Biru", 8000, 7, "Tablet"))
        Obat.listObat.add(Obat("Vitamin C 500gr isi 100", 82000, 12, "Kapsul"))
        History.listHistory.add(History("David", 84000, 3))
        History.listHistory.add(History("Daniel", 32000, 2))
        History.listHistory.add(History("David", 124000, 4))
        History.listHistory.add(History("Wete", 50000, 1))
        History.listHistory.add(History("Lawrence", 160000, 2))
        History.listHistory.add(History("Bima", 12000, 1))

        btLogin.setOnClickListener {
            indexUser = loginCheck()
            if (indexUser != -1) {
                if (indexUser == -100) {
                    val intent = Intent(this, AdminHomepageActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, CustomerHomepageActivity::class.java)
                    intent.putExtra("idxUser", indexUser)
                    startActivity(intent)
                }
            }
        }

        btToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginCheck(): Int {
        if (etUsername.text.isBlank() || etPassword.text.isBlank()) {
            // FIELD KOSONG
            Toast.makeText(this, "Field tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        } else {
            // CEK ADMIN
            if (etUsername.text.toString() == "admin" && etPassword.text.toString() == "admin") {
                return -100
            }

            // CEK USER
            for (user in User.listUser) {
                if (user.username == etUsername.text.toString()) {
                    // CEK PASSWORD
                    if (user.password == etPassword.text.toString()) {
                        return User.listUser.indexOf(user)
                    } else {
                        Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            // USER TIDAK DITEMUKAN
            Toast.makeText(this, "User tidak ditemukan!", Toast.LENGTH_SHORT).show()
        }
        return -1
    }
}