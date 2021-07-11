package com.fmahromi.lks_software_aplication_jabar_android_2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import com.fmahromi.lks_software_aplication_jabar_android_2021.api.ClietApi
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseUsers
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val dataRegister = intent.getStringExtra("register")
        if (dataRegister != null){
            Toast.makeText(this, "Berhasil Register User", Toast.LENGTH_SHORT).show()
        }

        val register = findViewById<Button>(R.id.btn_register)
        val loginActivity = findViewById<Button>(R.id.btn_login)
        val edtUsername = findViewById<TextInputEditText>(R.id.edt_username)
        val edtPassword = findViewById<TextInputEditText>(R.id.edt_password)

        register.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

        loginActivity.setOnClickListener {
            val username : String = edtUsername.text.toString()
            val password : String = edtPassword.text.toString()
            if (username.isNullOrBlank()){
                Toast.makeText(this,"Username Tidak boleh kosong",Toast.LENGTH_SHORT).show()
            }
            if (password.isNullOrBlank()){
                Toast.makeText(this,"Password Tidak boleh kosong",Toast.LENGTH_SHORT).show()
            }

            ClietApi.api.postLogin(username,password).enqueue(object : Callback<ResponseUsers>{
                override fun onResponse(
                    call: Call<ResponseUsers>,
                    response: Response<ResponseUsers>
                ) {
                    if (response.isSuccessful){
                       if (response.body()?.data != null){
                           val intent = Intent(this@MainActivity,MenuActivity::class.java)
                           intent.putExtra("username",response.body()?.data?.username)
                           startActivity(intent)
                       }else{
                           Toast.makeText(this@MainActivity, "username atau password salah", Toast.LENGTH_SHORT).show()
                       }
                    }else{
                        Toast.makeText(this@MainActivity, "Harap Hubungi Admin", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUsers>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Gagal Terhubung ke server", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}