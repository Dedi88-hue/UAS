package com.example.coffee.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffee.Domain.BannerModel
import com.example.coffee.Domain.CategoryModel
import com.example.coffee.Domain.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

// fungsi Bertanggung jawab untuk mengambil data dari Firebase Realtime Database.

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadBanner(): LiveData<MutableList<BannerModel>> { // untuk loadbanner fungsinya Ambil semua data banner dari node Banner
        val listData = MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabase.getReference("Banner")
        // GANTI ke addListenerForSingleValueEvent untuk mencegah memory leak
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BannerModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(BannerModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // LENGKAPI error handling
                Log.e("FirebaseError", "Error loading Banners: ${error.message}")
            }

        })
        return listData
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> { // untuk loadcategory fungsinya Ambil semua data banner dari node Banner
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        // GANTI ke addListenerForSingleValueEvent untuk mencegah memory leak
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // LENGKAPI error handling
                Log.e("FirebaseError", "Error loading Categories: ${error.message}")
            }

        })
        return listData
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>> {// untuk loadpopular fungsinya Ambil semua data banner dari node Banner
        val listData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Popular")
        // GANTI ke addListenerForSingleValueEvent untuk mencegah memory leak
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error loading Popular items: ${error.message}")
            }

        })
        return listData
    }

    fun loadItemCategory(categoryId: String): LiveData<MutableList<ItemsModel>> { //Ambil semua item berdasarkan ID kategori

        val itemLiveData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Items")
        val query: Query = ref.orderByChild("categoryId").equalTo(categoryId)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }
                }
                itemLiveData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // LENGKAPI error handling
                Log.e("FirebaseError", "Error loading items for category $categoryId: ${error.message}")
            }

        })
        return itemLiveData
    }
}