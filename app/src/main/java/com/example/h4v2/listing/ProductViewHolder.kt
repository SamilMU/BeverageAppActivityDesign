package com.example.h4v2.listing

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.h4v2.R
import com.example.h4v2.entities.ProductEntity
import com.example.h4v2.sharedObj.SharedObj

class ProductViewHolder(itemView: View,var mContext: Context, var itemClick: (product : ProductEntity) -> Unit) : RecyclerView.ViewHolder(itemView) {


    var tv_brand: TextView
    var tv_name: TextView
    var tv_price: TextView
    var iv_Add: AppCompatButton
    var iv_product: ImageView
    var viewHolderProduct: ProductEntity = ProductEntity("","",0.0,"","","")

    init {
        tv_brand = itemView.findViewById(R.id.tv_brand_card)
        tv_name = itemView.findViewById(R.id.tv_name_card)
        tv_price = itemView.findViewById(R.id.tv_price_card)
        iv_product = itemView.findViewById(R.id.iv_product_card)
        iv_Add = itemView.findViewById(R.id.btn_add_card)

        itemView.setOnClickListener {
            itemClick(viewHolderProduct)
        }
    }


    fun bindData(currentProduct: ProductEntity){
        viewHolderProduct = currentProduct
        tv_brand.text = currentProduct.brand
        tv_price.text = "${currentProduct.price}₺"
        tv_name.text = currentProduct.name



        iv_product.setImageResource(mContext.resources.getIdentifier(currentProduct.pic_name,"drawable",mContext.packageName))

        if(SharedObj.loggedIn){
            iv_Add.setOnClickListener {
                SharedObj.livePriceData.value = SharedObj.livePriceData.value!! + currentProduct.price
            }
        }else{
            iv_Add.visibility = View.GONE
        }

    }
}