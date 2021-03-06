package com.fmahromi.lks_software_aplication_jabar_android_2021

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fmahromi.lks_software_aplication_jabar_android_2021.api.ClietApi
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseMenu
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponsePost
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_update)

        val btnAddPost : Button = findViewById(R.id.btn_post_edit)
        val edtName = findViewById<TextInputEditText>(R.id.edt_post_name)
        val edtDescription = findViewById<TextInputEditText>(R.id.edt_post_description)
        val edtPrice = findViewById<TextInputEditText>(R.id.edt_post_price)

        val jenis = intent.getStringExtra("jenis")

        if (jenis == "post"){
            Toast.makeText(this, "post toast", Toast.LENGTH_SHORT).show()
            val name = edtName.text.toString()
            val description = edtDescription.text.toString()
            val price = edtPrice.text.toString()

            btnAddPost.setOnClickListener {
                Toast.makeText(this, name+" "+description+" "+price, Toast.LENGTH_SHORT).show()
//                ClietApi.api.postMenu(name,description,price.toInt()).enqueue(object : Callback<ResponsePost<Any>>{
//                    override fun onResponse(
//                        call: Call<ResponsePost<Any>>,
//                        response: Response<ResponsePost<Any>>
//                    ) {
//                        Toast.makeText(this@PostUpdateActivity , response.message().toString(), Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this@PostUpdateActivity,MenuActivity::class.java)
//                        startActivity(intent)
//                    }
//
//                    override fun onFailure(call: Call<ResponsePost<Any>>, t: Throwable) {
//                        Toast.makeText(this@PostUpdateActivity, "error", Toast.LENGTH_SHORT).show()
//                    }
//
//
//                })
            }

        }
        else{
            Toast.makeText(this, "Edit toast", Toast.LENGTH_SHORT).show()
            val id = Integer.parseInt(intent.getStringExtra("id"))

            edtName.setText(intent.getStringExtra("name"))
            edtDescription.setText(intent.getStringExtra("description"))
            edtPrice.setText(intent.getStringExtra("price"))

            val name = edtName.text.toString()
            val description = edtDescription.text.toString()
            val price = Integer.parseInt(edtPrice.text.toString())

            btnAddPost.setOnClickListener {
                ClietApi.api.putMenu(id,name,description,price)?.enqueue(object :Callback<ResponseMenu>{
                    override fun onResponse(
                        call: Call<ResponseMenu>,
                        response: Response<ResponseMenu>
                    ) {
                        Toast.makeText(this@PostUpdateActivity , "success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@PostUpdateActivity,MenuActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<ResponseMenu>, t: Throwable) {
                        Toast.makeText(this@PostUpdateActivity, "error", Toast.LENGTH_SHORT).show()
                    }
                } )
            }

        }
    }
}