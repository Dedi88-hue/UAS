package com.example.coffee.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffee.Domain.ItemsModel
import com.example.coffee.Helper.ChangeNumberItemsListener
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.databinding.ViewholderCartBinding

// fungsi Menampilkan item keranjang satu per satu di RecyclerView.
//Handle tombol +, - dan hapus item.
//Update total harga setiap ada perubahan jumlah item.
class CartAdapter
    (private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.Viewholder>() {
    class Viewholder (val binding: ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)

    private val managmentCart=ManagmentCart(context)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding=ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item=listItemSelected[position]

        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachitem.text="$${item.price}"
        holder.binding.totalEachitem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberitemTxt.text=item.numberInCart.toString()

        //Menampilkan gambar produk dari URL Firebase:
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply (RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)

        //Saat tombol ➕ ditekan: akan menambahkan numberInCart
        holder.binding.plusEachitem.setOnClickListener {
            managmentCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

        //Saat tombol ➖ ditekan: jika jumlah 1, item dihapus.
        holder.binding.minusEachitem.setOnClickListener {
            managmentCart.minusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

        //Saat tombol ❌ ditekan akan dihapus
        holder.binding.removeitemBtn.setOnClickListener {
            managmentCart.romveItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }


    }

    override fun getItemCount(): Int = listItemSelected.size

}