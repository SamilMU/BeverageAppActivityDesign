package com.example.h4v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.h4v2.databinding.ActivityDetailBinding
import com.example.h4v2.entities.ProductEntity
import com.example.h4v2.listing.ProductAdapter
import com.example.h4v2.sharedObj.SharedObj
import java.io.Serializable

class DetailActivity : AppCompatActivity() {

    private lateinit var product : ProductEntity
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productSerializable = intent.getSerializableExtra("product_object")
        getProductfromIntent(productSerializable)

        backBtnListener()
        addBtnListener()

        observe4CartTotal()
    }
    // Get Product
    fun getProductfromIntent(serializable: Serializable?){
        if(serializable!=null){
            product = serializable as ProductEntity
        }else{
            product = ProductEntity("","",0.0,"","","")
        }
        fillViews(product)
    }

    // View filling
    fun fillViews(productEntity: ProductEntity){
        binding.tvDetailBrandProduct.text = "${productEntity.brand}\n${productEntity.name}"
        binding.tvDetailSummary.text = productEntity.summary
        binding.tvDetailPrice.text = "${productEntity.price}₺"

        binding.ivDetailPic.setImageResource(resources.getIdentifier(productEntity.pic_name,"drawable",packageName))

        if(!SharedObj.loggedIn){
            binding.ivCartBtnToolbar.visibility = View.GONE
            binding.tvCartTotalToolbar.visibility = View.GONE
            binding.cvDetailAddCart.visibility = View.GONE
        }
    }

    // Click Listeners
    fun backBtnListener(){
        binding.ivBackBtnToolbar.setOnClickListener {
            onBackPressed()
        }
    }

    fun addBtnListener(){
        binding.ivAddCartDetail.setOnClickListener {
                SharedObj.livePriceData.value = SharedObj.livePriceData.value!! + product.price
        }
    }

    // LiveData Observer 4 Cart Total
    fun observe4CartTotal(){
        SharedObj.livePriceData.observe(this,{
            binding.tvCartTotalToolbar.text = "$it₺"
        })
    }

}