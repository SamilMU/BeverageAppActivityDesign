package com.example.h4v2.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.h4v2.R
import com.example.h4v2.entities.ProductEntity

class ProductAdapter(var mContext : Context, var productList: ArrayList<ProductEntity>, var itemClick: (product: ProductEntity) -> Unit) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val card = layoutInflater.inflate(R.layout.menu_card_item, parent, false)
        return ProductViewHolder(card, mContext, itemClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(productList.get(position))
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}