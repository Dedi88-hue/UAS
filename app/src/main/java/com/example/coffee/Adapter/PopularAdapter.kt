package com.example.coffee.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffee.Activity.DetailActivity
import com.example.coffee.Domain.ItemsModel
import com.example.coffee.databinding.ViewholderPupularBinding

//fungsi Adapter yang mengatur penampilan list produk populer ke RecyclerView.
//Menerima data ItemsModel dari ViewModel.

class PopularAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<PopularAdapter.Viewholder>() {
    lateinit var context: Context
    class Viewholder(val binding: ViewholderPupularBinding): RecyclerView.ViewHolder(binding.root){

    }

    //onCreateViewHolder: Membuat item view dari viewholder_popular.xml.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderPupularBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    //onBindViewHolder: Menampilkan:Nama produk ke titleTxt
    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()

        // glide untuk menampilkan Gambar dari URL menggunakan Glide ke pic
        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int  =items.size
}