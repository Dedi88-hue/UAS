package com.example.coffee.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffee.Domain.BannerModel
import com.example.coffee.Domain.CategoryModel
import com.example.coffee.Domain.ItemsModel
import com.example.coffee.Repository.MainRepository

// fungsi Menjadi penghubung antara UI (Activity) dan Repository.

// logika Setiap fungsi hanya meneruskan data LiveData dari repository ke UI.

class MainViewModel: ViewModel() {
private val repository=MainRepository()

    // contohnya
    fun loadBanner():LiveData<MutableList<BannerModel>>{ //Sebagai jembatan antara Repository dan Activity.
        return repository.loadBanner()
    }

    fun loadCategory():LiveData<MutableList<CategoryModel>>{ //Sebagai jembatan antara Repository dan Activity.
        return repository.loadCategory()
    }

    fun loadPopular():LiveData<MutableList<ItemsModel>>{  //Sebagai jembatan antara Repository dan Activity.
        return repository.loadPopular()
    }

    fun loadItem(categoryId:String):LiveData<MutableList<ItemsModel>>{ //Sebagai jembatan antara Repository dan Activity.
        return repository.loadItemCategory(categoryId)
    }
}