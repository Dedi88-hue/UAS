package com.example.coffee.Activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coffee.Domain.ItemsModel
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.R
import com.example.coffee.databinding.ActivityDetailBinding

//logika Data dikirim dari PopularAdapter
//atau bisa juga dari ItemListActivity (jika dari kategori).
//ItemsModel diserialisasi dan dikirim melalui intent.

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private lateinit var managementCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart=ManagmentCart(this)

        bundle()
        initSizeList()

    }

    //fungsi Memungkinkan user memilih satu dari tiga ukuran.
    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                largeBtn.setBackgroundResource(0)
            }
            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item= intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text=item.title
            descriptionTxt.text=item.description
            priceTxt.text="$"+item.price
            ratingTxt.text=item.rating.toString()

            // fungsi Menggunakan helper class ManagmentCart untuk menyimpan produk ke keranjang
            addToCartBtn.setOnClickListener {
                item.numberInCart=Integer.valueOf(
                    numberitemTxt.text.toString()
                )
                managementCart.insertItems(item)

            }

            backBtn.setOnClickListener {
                finish()
            }

            //Menambah/mengurangi jumlah pembelian.
            //Nilai numberInCart akan dikirim saat Add to Cart.
            plusCart.setOnClickListener {
                numberitemTxt.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }

            minusBtn.setOnClickListener {
                if (item.numberInCart>0){
                    numberitemTxt.text=(item.numberInCart-1).toString()
                    item.numberInCart--
                }
            }
        }

    }
}