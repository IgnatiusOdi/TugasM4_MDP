package com.example.tugas

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsernameLogin)
        etPassword = findViewById(R.id.etPasswordLogin)
        btLogin = findViewById(R.id.btLogin)
        btToRegister = findViewById(R.id.btToRegister)

        //DEBUG
        User.listUser.add(User("a", "a", "a", "12345678"))
        etUsername.setText("admin")
        etPassword.setText("admin")

        btLogin.setOnClickListener {
            indexUser = loginCheck()
            if (indexUser != -1) {
                if (indexUser == -100) {
                    val intent = Intent(this, AdminHomepageActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, CustomerHomepageActivity::class.java)
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