package com.example.coffee.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.Adapter.CartAdapter
import com.example.coffee.Helper.ChangeNumberItemsListener
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.R
import com.example.coffee.databinding.ActivityCartBinding
//fungsi Inisialisasi tampilan keranjang.
//Menampilkan item dari ManagmentCart.
//Menampilkan subtotal, pajak, ongkir, dan total harga.
class CartActivity : AppCompatActivity(){
    lateinit var binding: ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    private var tax:Double= 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        binding.apply {
            cartView.layoutManager=
                LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
            cartView.adapter=CartAdapter(
                // mg Mengambil daftar item dari keranjang yang tersimpan di TinyDB.
                managmentCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        calculateCart()
                    }

                }
            )
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    //Lalu dijumlahkan sebagai total akhir.
    private fun calculateCart() {
        val percentTax=0.02
        val delivery=15
        tax=Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100
        binding.apply {
            totalFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text="$$total"
        }
    }
}