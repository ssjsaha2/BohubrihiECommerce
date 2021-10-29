package com.example.bohubrihiecommerce.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.activities.ProductActivity
import com.example.bohubrihiecommerce.models.Product
import com.example.bohubrihiecommerce.utils.Constants

class ProductAdapter(val context: Context , val productList : ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {



    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        lateinit  var nameView : TextView
        lateinit var priceView : TextView
        lateinit var productImageView : ImageView


        init {
            nameView = itemView.findViewById(R.id.productName)
            priceView = itemView.findViewById(R.id.productPrice)
            productImageView = itemView.findViewById(R.id.productImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).
        inflate(R.layout.list_item_product,parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.nameView.text = productList.get(position).name
        holder.priceView.text = productList.get(position).price.toString()
        if(position%3 == 0){
            holder.productImageView.setBackgroundColor(context.resources.getColor(R.color.redSemiTransparent))
        }else if(position % 3 ==1){
            holder.productImageView.setBackgroundColor(context.resources.getColor(R.color.greenSemiTransparent))
        }else{
            holder.productImageView.setBackgroundColor(context.resources.getColor(R.color.blueSemiTransparent))
        }
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.PRODUCT_ID , productList.get(position).id)
            (context as ProductActivity).switchToProductDetailsFragment(bundle)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}