package com.fmahromi.lks_software_aplication_jabar_android_2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.fmahromi.lks_software_aplication_jabar_android_2021.api.ApiInterface
import com.fmahromi.lks_software_aplication_jabar_android_2021.api.ClietApi
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseUsers
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReqgisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reqgister)

        val edtUsername = findViewById<TextInputEditText>(R.id.edt_username)
        val edtPassword = findViewById<TextInputEditText>(R.id.edt_password)
        val edtPasswordConfirm = findViewById<TextInputEditText>(R.id.edt_confirm_password)

        val btnRegister = findViewById<Button>(R.id.btn_register)
        btnRegister.setOnClickListener {
            if (edtPassword.text.toString() != edtPasswordConfirm.text.toString()){
                Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_SHORT).show()
            }
            else{
                if (edtUsername.text.isNullOrBlank()){
                    Toast.makeText(this, "username tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
                if (edtPassword.text.isNullOrBlank()){
                    Toast.makeText(this, "password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
                ClietApi.api.postRegister(edtUsername.text.toString(),edtPassword.text.toString())
                    .enqueue(object : Callback<ResponseUsers>{
                        override fun onResponse(
                            call: Call<ResponseUsers>,
                            response: Response<ResponseUsers>
                        ) {
                            val intent = Intent(this@ReqgisterActivity,MainActivity::class.java)
                            intent.putExtra("register","berhasil Register User")
                            startActivity(intent)
                        }

                        override fun onFailure(call: Call<ResponseUsers>, t: Throwable) {
                            Toast.makeText(this@ReqgisterActivity, "Serever Error", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

    }
}