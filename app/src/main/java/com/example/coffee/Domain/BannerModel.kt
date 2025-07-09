package com.example.coffee.Domain

data class BannerModel(val url:String="") //Model data untuk menyimpan URL gambar banner dari Firebase.
//Saat Firebase mengembalikan data banner, data itu diubah ke objek BannerModel, dan URL-nya digunakan untuk menampilkan gambar di ImageView.