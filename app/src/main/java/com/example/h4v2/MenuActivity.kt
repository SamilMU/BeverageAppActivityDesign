package com.example.h4v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.h4v2.databinding.ActivityMenuBinding
import com.example.h4v2.entities.ProductEntity
import com.example.h4v2.listing.ProductAdapter
import com.example.h4v2.sharedObj.SharedObj
import com.google.android.material.chip.Chip

class MenuActivity : AppCompatActivity() {

    var productList = createTestProducts()
    lateinit var adap : ProductAdapter
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productList = createTestProducts()
        rvAdapter()

        authCheck()

        observe4CartTotal()
        backBtnListener()

        binding.menuChipGroup.setOnCheckedChangeListener { group, checkedId ->
            categoryChips(checkedId)
        }
    }

    // Adapter
    fun rvAdapter(){
        adap = ProductAdapter(this, productList, ::menu2Detail)
        binding.rvMenu.adapter = adap
        binding.rvMenu.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
    }

    // Test Product Function
    fun createTestProducts() : ArrayList<ProductEntity> {
        val tempList = ArrayList<ProductEntity>()
        val item1 = ProductEntity("Küçük Su","Erikli",1.35,"Su","500ml Su","eriklisu")
        val item2 = ProductEntity("Meyve Suyu","Dimes",14.35,"Meyve Suyu","Karışık Meyve Nektarı","meyvesuyu")
        val item3 = ProductEntity("Su","Hayat",14.35,"Su","Hayat Su Büyük","hayatbuyuksu")
        val item4 = ProductEntity("Gazoz","Sprite",9.35,"Gazli Icecek","2L Gazlı içecek","spritegaz")
        val item5 = ProductEntity("Soda","Beypazarı",4.35,"Maden Suyu","350ml Maden Suyu","beypazarisoda")
        val item6 = ProductEntity("Ayran","Sütaş",3.35,"Ayran","350ml İçimlik Ayran","ayran")
        tempList.add(item1)
        tempList.add(item2)
        tempList.add(item3)
        tempList.add(item4)
        tempList.add(item5)
        tempList.add(item6)

        return tempList
    }

    // Auth Check
    fun authCheck(){
        if(!SharedObj.loggedIn){
            binding.tvCartTotalToolbar.visibility = View.GONE
            binding.ivCartBtnToolbar.visibility = View.GONE
        }
    }

    // DetailPage Intent Function
    fun menu2Detail(detailProduct: ProductEntity){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("product_object",detailProduct)
        startActivity(intent)
    }

    // LiveData Observer 4 Cart Total
    fun observe4CartTotal(){
        SharedObj.livePriceData.observe(this,{
            binding.tvCartTotalToolbar.text = "$it₺"
        })
    }

    // Click Listeners
    fun backBtnListener(){
        binding.ivBackBtnToolbar.setOnClickListener {
            onBackPressed()
        }
    }

    // Category Chips
    fun categoryChips(checkedId : Int){
        var filteredProductList = ArrayList<ProductEntity>()
        productList = createTestProducts()
        binding.menuChipGroup.isSingleSelection = true
        if(checkedId == -1){
            filteredProductList = createTestProducts()
        }else{
            val chipView = findViewById<Chip>(checkedId)
            Log.e("Logcat",chipView.text.toString().lowercase())
            when (chipView.text.toString().lowercase()) {
                "su" -> {
                    productList.forEach {
                        if (it.category.lowercase() == "su") {
                            filteredProductList.add(it)
                        }
                    }
                }
                "maden suyu" -> {
                    productList.forEach {
                        if (it.category.lowercase() == "maden suyu") {
                            filteredProductList.add(it)
                        }
                    }
                }
                "meyve suyu" -> {
                    productList.forEach {
                        if (it.category.lowercase() == "meyve suyu") {
                            filteredProductList.add(it)
                        }
                    }
                }
                "gazlı i̇çecek" -> {
                    productList.forEach {
                        if (it.category.lowercase() == "gazli icecek") {
                            filteredProductList.add(it)
                        }
                    }
                }
                "ayran" -> {
                    productList.forEach {
                        if (it.category.lowercase() == "ayran") {
                            filteredProductList.add(it)
                        }
                    }
                }
            }
        }
        productList = filteredProductList
        rvAdapter()
    }

}