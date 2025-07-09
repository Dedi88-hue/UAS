package com.example.coffee.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.Adapter.ItemListCategoryAdapter
import com.example.coffee.R
import com.example.coffee.ViewModel.MainViewModel
import com.example.coffee.databinding.ActivityItemListBinding

// fungsi Menampilkan daftar produk dari kategori tertentu yang dipilih user.
class ItemListActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemListBinding
    private val viewModel=MainViewModel()
    private var id:String=""
    private var title:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList() {
        val emptyAdapter = ItemListCategoryAdapter(mutableListOf()) // <- adapter kosong dulu
        binding.cartView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter = emptyAdapter

        viewModel.loadItem(id).observe(this@ItemListActivity) { itemList ->
            itemList?.let {
                emptyAdapter.items.clear()
                emptyAdapter.items.addAll(it)
                emptyAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.backBtn.setOnClickListener { finish() }
    }


    private fun getBundle() {
        id=intent.getStringExtra("id")?:""
        title=intent.getStringExtra("title")?:""

        binding.categoryTxt.text=title
    }
}