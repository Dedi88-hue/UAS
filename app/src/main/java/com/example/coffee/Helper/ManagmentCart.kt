package com.example.coffee.Helper

import android.content.Context
import android.widget.Toast
import com.example.coffee.Domain.ItemsModel
import com.example.coffee.Helper.TinyDB
import com.example.coffee.Helper.ChangeNumberItemsListener


// fungsi ManagmentCart adalah helper class yang
// berfungsi untuk mengelola keranjang belanja. Kelas ini menangani:
class ManagmentCart(val context: Context) {

    private val tinyDB = TinyDB<ItemsModel>(context)

    //Menambahkan item ke keranjang
    fun insertItems(item: ItemsModel) {
        val listItem = getListCart()
        val existAlready = listItem.any { it.title == item.title }
        val index = listItem.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listItem[index].numberInCart = item.numberInCart
        } else {
            listItem.add(item)
        }
        tinyDB.putListObject("CartList", listItem)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    //Mengambil data isi keranjang
    fun getListCart(): ArrayList<ItemsModel> {
        return tinyDB.getListObject("CartList", ItemsModel::class.java) ?: arrayListOf()
    }

    //Menambah atau mengurangi jumlah item dalam keranjang
    fun minusItem(listItems: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (listItems[position].numberInCart == 1) {
            listItems.removeAt(position)
        } else {
            listItems[position].numberInCart--
        }
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }
    //Menghapus item dari keranjang
    fun romveItem(listItems: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {

        listItems.removeAt(position)

        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    fun plusItem(listItems: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        listItems[position].numberInCart++
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    //Menghitung total biaya (getTotalFee)
    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0
        for (item in listItem) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}