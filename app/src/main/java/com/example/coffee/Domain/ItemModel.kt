package com.example.coffee.Domain

import android.icu.text.CaseMap.Title
import java.io.Serializable

data class ItemsModel(
    var title: String="",
    var description: String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var price:Double=0.0,
    var rating:Double=0.0,
    var numberInCart:Int=0,
    var extra:String="",
) : Serializable

//fungsi Menyimpan informasi produk kopi seperti nama, gambar, harga, dan rating.
// logika Model ini digunakan untuk mengambil data dari Firebase (Popular / Items node).