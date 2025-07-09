package com.example.coffee.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffee.Adapter.CategoryAdapter
import com.example.coffee.Adapter.PopularAdapter
import com.example.coffee.ViewModel.MainViewModel
import com.example.coffee.databinding.ActivityMainBinding

//fungsi Menampilkan data ke user (UI)

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // on create logika Memanggil semua fungsi init:

        initBanner() //Observe LiveData dari viewModel.loadBanner()
        iniCategory() //Ambil list kategori dari Firebase
        iniPopular() // Ambil list item populer, Tampilkan di RecyclerView grid lewat PopularAdapter
        initBottomMenu() //Klik tombol Cart → buka CartActivity pakai Intent
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility=View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.banner)
            binding.progressBarBanner.visibility=View.GONE
        }

        viewModel.loadBanner()
    }

    private fun iniCategory(){
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.recyclerViewCat.layoutManager=
                LinearLayoutManager(this@MainActivity
                    ,LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewCat.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        }
        viewModel.loadCategory()
    }

    private fun iniPopular(){
        binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.loadPopular().observeForever {
            binding.recyclerViewPopular.layoutManager=GridLayoutManager(this,2)
            binding.recyclerViewPopular.adapter=PopularAdapter(it)
            binding.progressBarPopular.visibility= View.GONE
        }

        viewModel.loadPopular()
    }
}