package com.fmahromi.lks_software_aplication_jabar_android_2021

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fmahromi.lks_software_aplication_jabar_android_2021.api.ClietApi
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.DataItem
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseMenu

class ItemAdapter(val context: Context) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    var menuList : ResponseMenu ?= null

    fun delete(id : Int) {
        ClietApi.api.deleteMenu(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.MyViewHolder, position: Int) {

        holder.txtMenuId?.text = menuList?.data?.get(position)?.id ?:""
        holder.txtMenuName?.text = menuList?.data?.get(position)?.name ?:""
        holder.txtMenuDescription?.text = menuList?.data?.get(position)?.description ?:""
        holder.txtMenuPrice?.text = menuList?.data?.get(position)?.price ?:""

        holder.btnEdit?.setOnClickListener {
            val intent = Intent(context,PostUpdateActivity::class.java)
            intent.putExtra("id",holder.txtMenuId?.text.toString())
            intent.putExtra("name",holder.txtMenuName?.text.toString())
            intent.putExtra("description",holder.txtMenuDescription?.text.toString())
            intent.putExtra("price",holder.txtMenuPrice?.text.toString())
            intent.putExtra("jenis","update")
            context.startActivity(intent)
        }

        holder.btnDelete?.setOnClickListener {
            val id : Int = Integer.parseInt(holder.txtMenuId?.text.toString())
            delete(id)
            notifyDataSetChanged()
        }




    }

    override fun getItemCount(): Int {
        return menuList?.data?.size?:0
    }

    fun setMenuListItems(menuList: ResponseMenu?){
        if (menuList != null) {
            this.menuList = menuList
            notifyDataSetChanged()
        }

    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val txtMenuId : TextView? = itemView?.findViewById(R.id.txt_menu_id)
        val txtMenuName : TextView? = itemView?.findViewById(R.id.txt_menu_name)
        val txtMenuDescription : TextView? = itemView?.findViewById(R.id.txt_menu_description)
        val txtMenuPrice : TextView? = itemView?.findViewById(R.id.txt_menu_price)
        val btnDelete : ImageButton? = itemView?.findViewById(R.id.btn_delete)
        val btnEdit : ImageButton? = itemView?.findViewById(R.id.btn_edit)







    }


}