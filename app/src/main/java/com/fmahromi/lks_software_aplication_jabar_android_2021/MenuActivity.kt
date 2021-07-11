package com.fmahromi.lks_software_aplication_jabar_android_2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fmahromi.lks_software_aplication_jabar_android_2021.api.ClietApi
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseMenu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnAddMenu = findViewById<FloatingActionButton>(R.id.fab)

        btnAddMenu.setOnClickListener {
            val intent = Intent(this,PostUpdateActivity::class.java)
            intent.putExtra("jenis","post")
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rv_menu)
        recyclerAdapter = ItemAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        ClietApi.api.getMenu().enqueue(object : Callback<ResponseMenu>{
            override fun onResponse(call: Call<ResponseMenu>, response: Response<ResponseMenu>) {
                if(response?.body() != null){
                    recyclerAdapter.setMenuListItems(response.body())}
            }

            override fun onFailure(call: Call<ResponseMenu>, t: Throwable) {
                Toast.makeText(this@MenuActivity, "error", Toast.LENGTH_SHORT).show()
            }


        })


    }
}